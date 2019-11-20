package org.hyperledger.fabric.ledger;

public interface StateModification {
	
    /**
     * Returns the transaction id.
     *
     * @return tx id of modification
     */
    String getTxId();

    /**
     * Returns the state at the time returned by {@link #getTimestamp()}.
     *
     * @return value
     */
    State getState();

    /**
     * Returns the timestamp of the key modification entry.
     *
     * @return timestamp
     */
    java.time.Instant getTimestamp();

    /**
     * Returns the deletion marker.
     *
     * @return is key was deleted
     */
    boolean isDeleted();
}
