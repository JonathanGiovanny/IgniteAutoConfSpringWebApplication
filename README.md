# IgniteAutoConfSpringWebApplication
Ignite project with Spring boot.

It does now works without many issues, it seems so.

Run with the following arguments:
```
-Xmx512m -Xms512m -XX:MaxDirectMemorySize=512m  -XX:+DisableExplicitGC -javaagent:C:/Users/jcamargos/.m2/repository/org/springframework/springloaded/1.2.8.RELEASE/springloaded-1.2.8.RELEASE.jar -noverify
```

Waiting for 2.7.0 release of apache Ignite to solve issues with spring boot.

**Issues**
- It requires an IgniteCache instance for spring boot otherwise it generates an error that only the new release will solve (or I touch the code, which will not happen soon).
- It needs to load ALL the data into the memory to access all data or query record by record, otherwise it will not get the data directly from the DB, which could drive into Out Of Memory Error.
- Don't remember
