package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.*;

/**
 * Created with IntelliJ IDEA.
 * User: ex3ndr
 * Date: 03.11.13
 * Time: 20:46
 */
public class MTMessage extends TLObject {
    private long messageId;
    private int seqNo;
    private byte[] content;

    public MTMessage(long messageId, int seqNo, byte[] content) {
        this.messageId = messageId;
        this.seqNo = seqNo;
        this.content = content;
    }

    public MTMessage() {

    }

    @Override
    public int getClassId() {
        return 0;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(messageId, stream);
        writeInt(seqNo, stream);
        writeInt(content.length, stream);
        writeByteArray(content, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        messageId = readLong(stream);
        seqNo = readInt(stream);
        int size = readInt(stream);
        content = readBytes(size, stream);
    }

    @Override
    public String toString() {
        return "message";
    }
}
