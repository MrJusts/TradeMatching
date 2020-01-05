[![Quality Gate](http://127.0.0.1:9000/api/badges/gate?key=Corda)](http://localhost:9000/dashboard/index/com.qualinsight.plugins.sonarqube:qualinsight-plugins-sonarqube-badges)

# Trade Matching

The goal is to create a simple example of how CordApp can be used in post-trade activities for FX Trade confirmation matching (SWIFT MT300) between counterparties.
Example currently can be executed from command line (CRaSH shell). The following instruction will provided more detailed information:

## Build

From command line run:
    
    ./gradlew clean deployNodes

## Running the nodes

After build is complete on the same location run:

    build/nodes/runnodes

## Interacting with the nodes

In the following scenario PartyA sends FX trade confirmation message to PartyB. The PartyB signs and accepts transaction.

#### Upload attachment (SWIFT message) to PartyA node:

In this case MT300_FX.txt.zip file will be uploaded to PartyA node:

![Screen shot](https://user-images.githubusercontent.com/729955/71601262-51a4e200-2b5b-11ea-8eb0-ee8de7faabd6.png)

Now ID of attachment shall be provided after upload is successful as in following image:

![Screen shot](https://user-images.githubusercontent.com/729955/71601633-fecc2a00-2b5c-11ea-97c0-04aba00024f6.png)

Start Flow on "PartyA" node and type:
    
    start IOUFlow iouValue: 99, otherParty: "O=PartyB,L=New York,C=US", attachmentHash: <ID_of_attachment>

![Screen shot](https://user-images.githubusercontent.com/729955/71601889-338cb100-2b5e-11ea-8e1b-b402461dd7b9.png)

Now transaction is completed, trade matching in this scenario is successful, since PartyB signed the transaction.

### Node information

Query the results on each node by running command:
    
    run vaultQuery contractStateType: com.template.state.IOUState

Each node now holds the same attachmentHash (ID) and other information regards to transaction: 

![Screen shot](https://user-images.githubusercontent.com/729955/71602210-e3aee980-2b5f-11ea-91fe-9b4e8e948037.png)

To download and save attachment type on PartyB node following command:

    run openAttachment id: <ID>

In this scenario I saved the attachment as SWIFT.ZIP:

![Screen shot](https://user-images.githubusercontent.com/729955/71602455-245b3280-2b61-11ea-97b0-e74ee5861e79.png)

Now SWIFT MT300 message sent from PartyA is available at PartyB node location.