# CodeReviewLab <br>
+ Demo Gadget chains <br>
Build flask env <br>
navigate to /demo/ssti<br>
exploit ssti with gadgat chain <br>
+ Demo Lab RMI Insecure Deserialization <br>
exploit with gadget chains <br>
You can download/clone this lab from github <br>
- First, import all java file to your ide <br>
- Run DemoServer jar file on Terminal/CMD ( java -jar <jarfile>) <br>
- run client with IDE. <br>

This is RMI client/server which connected to each other and send Object data with Serialize/Deserialize  <br>

You can custom object from client-side with Reflection and change private value <br>

Use gadget chains from BadAttributeValueExpException class to trigger toString() method and excute arbitrary command (ex: whoami) <br>


