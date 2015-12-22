relax 
====

The main purpouse of this project is to serve as a boostrap into the world of reactive programming
 for busy Java Developers (like myself). 
 
 relax is a work in progress micro rest server that enables creating actor based backends. 

### Hard dependencies
-------------------------
Java 8, Spring , netty

### Hello World server
-------------------------

There are few steps necessary to boot your first reactive server

> Clone the repo & install fuse locally, then add as dependency.

```xml
<dependency>
    <groupId>com.rockey</groupId>
    <artifactId>relax</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

> Create relax.conf in resources folder of your new project. This is where we configure our routes.

```
routes {
    GET /hello {
        class : test.endpoint.HelloWorld
    }
}
actors {

}
```

> Create spring application context.xml file. Relax currently requires spring to operate.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	   xmlns:context="http://www.springframework.org/schema/context"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans
						   http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
						   http://www.springframework.org/schema/context
						   http://www.springframework.org/schema/context/spring-context-4.0.xsd"
       default-init-method="init"
>

    <context:annotation-config />
    <context:component-scan base-package="com.rockey.relax" />
    <bean class="com.rockey.relax.codec.JsonWireCodec" />

</beans>
```


