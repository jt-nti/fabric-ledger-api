/*
SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.ledger;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

public interface Collection extends Iterable<State> {
	
    String WORLD = "worldstate";

	/**
     * Returns the value of the specified <code>key</code> from the ledger.
     * <p>
     * Note that getState doesn't read data from the writeset, which has not been committed to the ledger.
     * In other words, GetState doesn't consider data modified by PutState that has not been committed.
     *
     * @param key name of the value
     * @return value the value read from the ledger
     */
    State getState(StateKey key);
    
    /**
     * Puts the specified <code>key</code> and <code>value</code> into the transaction's
     * writeset as a data-write proposal.
     * <p>
     * putState doesn't effect the ledger
     * until the transaction is validated and successfully committed.
     * Simple keys must not be an empty string and must not start with 0x00
     * character, in order to avoid range query collisions with
     * composite keys
     *
     * @param key   name of the value
     * @param value the value to write to the ledger
     */
    void putState(StateKey key, State value);
    
    /**
     * Records the specified <code>key</code> to be deleted in the writeset of
     * the transaction proposal.
     * <p>
     * The <code>key</code> and its value will be deleted from	
     * the ledger when the transaction is validated and successfully committed.
     *
     * @param key name of the value to be deleted
     */
    void deleteState(StateKey key);
    
    /**
     * Returns all existing keys, and their values, that are lexicographically
     * between <code>startkey</code> (inclusive) and the <code>endKey</code>
     * (exclusive).
     * <p>
     * The keys are returned by the iterator in lexical order. Note
     * that startKey and endKey can be StateKey.EMPTY, which implies unbounded range
     * query on start or end.
     * <p>
     * The keys may be created as partial keys
     * <p>
     * Call close() on the returned {@link QueryResultsIterator#close()} object when done.
     *
     * @param startKey key as the start of the key range (inclusive)
     * @param endKey   key as the end of the key range (exclusive)
     * @return an {@link Iterable} of {@link KeyValue}
     */
    StateList getStateByRange(StateKey startKey, StateKey endKey);
    
    /**
     * Performs a "rich" query against a state database.
     * <p>
     * It is only supported for state databases that support rich query,
     * e.g. CouchDB. The query string is in the native syntax
     * of the underlying state database. An {@link QueryResultsIterator} is returned
     * which can be used to iterate (next) over the query result set.
     *
     * @param query query string in a syntax supported by the underlying state
     *              database
     * @return {@link QueryResultsIterator} object contains query results
     * @throws UnsupportedOperationException if the underlying state database does not support rich
     *                                       queries.
     */
    StateList getQueryResult(String query);
   
    /**
     * Returns a history of key values across time.
     * <p>
     * For each historic key update, the historic value and associated
     * transaction id and timestamp are returned. The timestamp is the
     * timestamp provided by the client in the proposal header.
     * This method requires peer configuration
     * <code>core.ledger.history.enableHistoryDatabase</code> to be true.
     *
     * @param key The state variable key
     * @return an {@link Iterable} of {@link KeyModification}
     */
     StateHistory getHistory(StateKey key);
    
}
