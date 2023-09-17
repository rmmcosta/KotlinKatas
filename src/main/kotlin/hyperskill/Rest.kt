package hyperskill

/*
Six REST principles
Abiding by the following six important principles guarantees that you write a RESTful service:

Client-server interaction model. By separating the user interface from the data warehouse server, we improve and simplify application operations.

Stateless. Each request from a client to a server must contain all necessary information and cannot rely on any state stored on the server side.
According to REST, the service does not store the results of the previous operation. Simply put, it works according to the "Asked, answered, forgotten" concept.

Cacheable. A request-response pair can be marked as cached and stored on the user side.
It is possible to draw an analogy to web page caching: if a page was downloaded once, it could be accessed without addressing the server again.

Uniform interface. REST architecture specifies that any REST service must be understandable without its developer.

Layered system. A client cannot just tell whether it is connected directly to the end server or an intermediary along the way.

Code on demand [Optional]. On request, the service must give executable code in the form of an applet or script to be executed on the client's side. In practice, it is very seldom used.
 */