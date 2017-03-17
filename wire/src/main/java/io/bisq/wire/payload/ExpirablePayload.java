package io.bisq.wire.payload;


/**
 * Messages which support a time to live
 * <p>
 * Implementations:
 *
 * @see StoragePayload
 * @see MailboxStoragePayload
 */
public interface ExpirablePayload extends Payload {
    /**
     * @return Time to live in milli seconds
     */
    long getTTL();
}