/*
SPDX-License-Identifier: Apache-2.0
*/

package org.hyperledger.fabric.ledger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hyperledger.fabric.contract.annotation.DataType;

/**
 * Represents the things that are contained within the Collections.
 * In the notes - this is referred to as a 'state'.
 * 
 * Concerned that this naming is reflecting an implementation choice.
 * and there are APIs on states that 
 *
 * @param <T>
 */
public interface State extends PolicyManaged {

	static <T> State from(T obj) {
		return null;
	}
	
	/**
	 * Returns the full object that this state is encapsulating
	 * @return
	 */
	<T> T to(Class<T> clz);
	
    /**
     * @return the private data hash
     */
    byte[] getPrivateDataHash();
	
    /**
     * Retrieves the key-level endorsement
     * policy for the private data specified by <code>key</code>. Note that this introduces
     * a read dependency on <code>key</code> in the transaction's readset.
     *
     * @return Key Level endorsement as byte array
     */
    byte[] getPrivateDataValidationParameter();
	
    /**
     * Sets the key-level endorsement policy for the private data specified by <code>key</code>.
     *
     * @param value endorsement policy
     */
    void setPrivateDataValidationParameter(byte[] value);
    
    /**
     * Adds the specified orgs to the list of orgs that are required
     * to endorse. All orgs MSP role types will be set to the role that is
     * specified in the first parameter. Among other aspects the desired role
     * depends on the channel's configuration: if it supports node OUs, it is
     * likely going to be the PEER role, while the MEMBER role is the suited
     * one if it does not.
     *
     * @param roleType the MSP role type
     * @param organizations the list of organizations
     */
    void addOrgs(RoleType roleType, String... organizations);

    /**
     * deletes the specified channel orgs from the existing key-level endorsement
     * policy for this KVS key.
     * @param organizations the list of organizations
     */
    void deleteOrgs(String... organizations);

    /**
     * Returns an array of channel orgs that are required to endorse changes
     *
     * @return List of organizations
     */
    List<String> listOrgs();

    /**
     * RoleType of an endorsement policy's identity
     */
    public enum RoleType {
        /**
         * RoleTypeMember identifies an org's member identity
         */
        RoleTypeMember("MEMBER"),
        /**
         * RoleTypePeer identifies an org's peer identity
         */
        RoleTypePeer("PEER");

        private String val;

        RoleType(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }

        static Map<String, RoleType> reverseLookup = new HashMap<>();

        static {
            for (RoleType item : RoleType.values()) {
                reverseLookup.put(item.getVal(), item);
            }
        }

        public static RoleType forVal(String val) {
            if (!reverseLookup.containsKey(val)) {
                throw new IllegalArgumentException("role type "+ val + " does not exist");
            }
            return reverseLookup.get(val);
        }
    }


}
