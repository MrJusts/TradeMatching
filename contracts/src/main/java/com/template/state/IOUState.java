package com.template.state;

import com.template.contract.IOUContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.Arrays;
import java.util.List;

// *********
// * State *
// *********

@BelongsToContract(IOUContract.class)
public class IOUState implements ContractState {
    private final int value;
    private final Party buyer;
    private final Party seller;
    private final SecureHash attachmentHash;
    private final UniqueIdentifier hashID;


    public IOUState(int value, Party buyer, Party seller, SecureHash attachmentHash) {
        this.value = value;
        this.buyer = buyer;
        this.seller = seller;
        this.attachmentHash = attachmentHash;
        this.hashID = new UniqueIdentifier();
    }


    public int getValue() {
        return value;
    }

    public Party getBuyer() {
        return buyer;
    }

    public Party getSeller() {
        return seller;
    }

    public SecureHash getAttachmentHash() {
        return attachmentHash;
    }

    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(buyer, seller);
    }
}
