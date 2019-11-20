/*
SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.ledger;

/** Indicates that there is a policy that affects this object.
 * 
 * Typically an Endorsement policy
 * 
 */
public interface PolicyManaged {

	
	/** gets the policy that affects this
	 * 
	 */
	Policy getPolicy();
	
	/** sets the policy to affect this object
	 * 
	 */
	void setPolicy(String someStringVersionOfThePolicy);
}
