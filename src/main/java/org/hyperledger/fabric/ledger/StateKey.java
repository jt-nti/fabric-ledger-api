/*
SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.ledger;

public interface StateKey {

	public static StateKey EMPTY = null; // impl later
	
	/**
	 * Creates the key out of a set of elements for the given class
	 * 
	 * May be partial
	 * 
	 * @param <T>
	 * @param elements
	 * @param clz
	 * @return
	 */
	static StateKey from(String... elements) {
		return null;
	}

	

	/**
	 * Generates the string representation used by the underlying blockchain
	 * 
	 * @return
	 */
	String toString();

}
