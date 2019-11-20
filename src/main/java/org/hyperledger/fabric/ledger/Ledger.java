/*
SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.ledger;

import org.hyperledger.fabric.shim.Chaincode.Response;

/**
 * Network Shared Transaction Data
 * 
 */
public interface Ledger {

	/**
	 * Get the ledger that represents the current ledger state.
	 * 
	 *  
	 * @return
	 */
	public static Ledger getLedger() {
		return null;
	};
	
	/** Used to return the ledger to invoke named transaction on this.
	 * (previously referred to as cross-channel invocation)
	 * 
	 * Note that ledger will be in read-only state. 
	 * 
	 * @param name
	 * @return
	 */
	public static Ledger getLedger(String name) {
		return null;
	};
		
	/** Return the general collection based on the supplied name
	 * Named ones are the private data collections
	 * 
	 * @param name
	 * @return
	 */
	Collection getCollection(String name);
	
	/**
	 * Return the World State collection
	 * 
	 * @return
	 */
	Collection getCollection();

    /**
     * Locally calls the specified chaincode <code>invoke()</code> using the
     * same transaction context.
     * <p>
     * chaincode calling chaincode doesn't create a new transaction message.
     * <p>
     * If the called chaincode is on the same channel, it simply adds the called
     * chaincode read set and write set to the calling transaction.
     * <p>
     * If the called chaincode is on a different channel,
     * only the Response is returned to the calling chaincode; any <code>putState</code> calls
     * from the called chaincode will not have any effect on the ledger; that is,
     * the called chaincode on a different channel will not have its read set
     * and write set applied to the transaction. Only the calling chaincode's
     * read set and write set will be applied to the transaction. Effectively
     * the called chaincode on a different channel is a `Query`, which does not
     * participate in state validation checks in subsequent commit phase.
     * <p>
     * If `channel` is empty, the caller's channel is assumed.
     * <p>
     * Invoke another chaincode using the same transaction context.
     *
     * @param chaincodeName Name of chaincode to be invoked.
     * @param args          Arguments to pass on to the called chaincode.
     * @param channel       If not specified, the caller's channel is assumed.
     * @return {@link Response} object returned by called chaincode
     */
	Object invokeTransaction(String chaincodeName, String channel, String txFunctionName, Object... args);	
	Object invokeTransaction(String chaincodeName, String txFunctionName, Object... args);
	Object invokeTransaction(String txFunctionName, Object... args);	

}
