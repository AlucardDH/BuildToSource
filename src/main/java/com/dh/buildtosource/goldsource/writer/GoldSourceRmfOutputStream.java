package com.dh.buildtosource.goldsource.writer;

import com.dh.buildtosource.goldsource.model.rmf.*;
import lombok.AllArgsConstructor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

@AllArgsConstructor
public class GoldSourceRmfOutputStream implements AutoCloseable {

    private DataOutputStream os;

    public void writeFixedLengthString(String str,int size) throws IOException {
        int currentSize = Math.min(str.length(),size-1);
        String completedStr =
                str.substring(0,currentSize)
                + " ".repeat(size-currentSize-1)
                + Character.MIN_VALUE;

        os.writeChars(completedStr);
    }

    public void writeVariableLengthString(String str) throws IOException {
        int length = str.length()+1;
        os.writeByte(length);
        os.writeChars(str+Character.MIN_VALUE);
    }

    public void writeEmptyBytes(int bytes) throws IOException {
        byte[] empty = new byte[bytes];
        Arrays.fill(empty, (byte) 0);
        os.write(bytes);
    }



    public void writeGoldSourceRmfMap(GoldSourceRmfMap map) throws IOException {
        os.writeFloat(map.getVersion());
        writeFixedLengthString(map.getFormat(),4);
        os.writeInt(map.getVisGroupsCount());
        for(VisGroup visGroup : map.getVisGroups()) {
            writeVisGroup(visGroup);
        }
        writeVariableLengthString(map.getType());

        writeEmptyBytes(7);

        os.writeInt(map.getObjectsCount());
        for(AbstactObject object : map.getObjects()) {
            writeObject(object);
        }

        // worldspawn
        writeVariableLengthString(map.getWorldSpawnEntityClassname());
        writeEmptyBytes(4);
        os.writeInt(map.getWorldSpawnEntityFlags());
        os.writeInt(map.getWorldSpawnEntityKeyValuesCount());
        for(String keyValue : map.getWorldSpawnEntityKeyValues()) {
            writeVariableLengthString(keyValue);
        }

        writeEmptyBytes(12);

        os.writeInt(map.getPathsCount());
        for(Path path : map.getPaths()) {
            writePath(path);
        }

        writeFixedLengthString(map.getDocInfo(),8);

        os.writeFloat(map.getCameraVersion());
        os.writeInt(map.getActiveCamera());
        os.writeInt(map.getCamerasCount());
        for(Camera camera : map.getCameras()) {
            writeCamera(camera);
        }


    }

    public void writeObject(AbstactObject object) throws IOException {
        // common
        writeVariableLengthString(object.getType());
        os.writeInt(object.getVisGroup());
        writeColor(object.getColor());

        if(object instanceof Group) {
            writeGroup((Group) object);
        } else if(object instanceof Entity) {
            writeEntity((Entity) object);
        } else if(object instanceof Solid) {
            writeSolid((Solid) object);
        }
    }

    public void writeColor(Color color) throws IOException {
        os.write(new byte[]{color.getRed(),color.getGreen(),color.getBlue()});
    }

    public void writeGroup(Group group) throws IOException {
        os.writeInt(group.getObjectsCount());
        for(AbstactObject object : group.getObjects()) {
            writeObject(object);
        }
    }

    public void writeEntity(Entity entity) throws IOException {
        os.writeInt(entity.getBrushesCount());
        for(Solid brush : entity.getBrushes()) {
            writeSolid(brush);
        }
        writeVariableLengthString(entity.getClassname());
        writeEmptyBytes(4);
        os.writeInt(entity.getFlags());
        os.writeInt(entity.getKeyValueCount());
        for(String keyValue : entity.getKeyValues()) {
            writeVariableLengthString(keyValue);
        }
        writeEmptyBytes(14);
        writeVector(entity.getPosition());
        writeEmptyBytes(4);
    }

    public void writeSolid(Solid brush) throws IOException {
        writeEmptyBytes(4);
        os.writeInt(brush.getFacesCount());
        for(Face face : brush.getFaces()) {
            writeFace(face);
        }
    }

    public void writeVisGroup(VisGroup visGroup) throws IOException {
        writeFixedLengthString(visGroup.getName(),128);
        writeColor(visGroup.getColor());
        writeEmptyBytes(1);
        os.writeInt(visGroup.getIndex());
        os.write(visGroup.getVisibility());
        writeEmptyBytes(3);
    }

    public void writePath(Path path) throws IOException {
        writeFixedLengthString(path.getPathName(),128);
        writeFixedLengthString(path.getPathClass(),128);
        os.writeInt(path.getType());
        os.writeInt(path.getCornersCount());
        for(Corner corner : path.getCorners()) {
            writeCorner(corner);
        }
    }

    public void writeCorner(Corner corner) throws IOException {
        writeVector(corner.getPosition());
        os.writeInt(corner.getIndex());
        writeFixedLengthString(corner.getNameOverride(),128);
        os.writeInt(corner.getKeyValueCount());
        for(String keyValue : corner.getKeyValues()) {
            writeVariableLengthString(keyValue);
        }
    }

    public void writeCamera(Camera camera) throws IOException {
        writeVector(camera.getEyePosition());
        writeVector(camera.getTargetPosition());
    }

    public void writeVector(Vector vector) throws IOException {
        os.writeFloat(vector.getX());
        os.writeFloat(vector.getY());
        os.writeFloat(vector.getZ());
    }

    public void writeFace(Face face) throws IOException {
        writeFixedLengthString(face.getTextureName(),256);
        writeEmptyBytes(4);

        writeVector(face.getUAxis());
        os.writeFloat(face.getXShift());
        writeVector(face.getVAxis());
        os.writeFloat(face.getYShift());

        os.writeFloat(face.getRotation());

        os.writeFloat(face.getXScale());
        os.writeFloat(face.getYScale());

        writeEmptyBytes(16);

        os.writeInt(face.getVerticesCount());
        for(Vector vertex : face.getVertices()) {
            writeVector(vertex);
        }
        for(Vector vertex : face.getPlaneVertices()) {
            writeVector(vertex);
        }

    }


    @Override
    public void close() throws Exception {
        try {
            os.close();
        } catch (Exception e) {
            // nothing more to close
        }
    }
}
