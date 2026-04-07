# DataMammoth Java SDK

Official Java client for the [DataMammoth API v2](https://data-mammoth.com/api-docs/reference).

> **Status**: Under development. Not yet published to Maven Central.

## Installation

### Maven

```xml
<dependency>
    <groupId>com.datamammoth</groupId>
    <artifactId>datamammoth-java</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.datamammoth:datamammoth-java:0.1.0'
```

## Quick Start

```java
import com.datamammoth.DataMammoth;
import com.datamammoth.model.Server;
import com.datamammoth.model.ServerListParams;
import com.datamammoth.model.Task;

public class Example {
    public static void main(String[] args) {
        DataMammoth dm = DataMammoth.builder()
            .apiKey("dm_your_key_here")
            .build();

        // List active servers
        var servers = dm.servers().list(ServerListParams.builder()
            .status("active")
            .build());
        for (Server server : servers.getData()) {
            System.out.printf("%s — %s%n", server.getHostname(), server.getIpAddress());
        }

        // Create a server
        Task task = dm.servers().create(ServerCreateParams.builder()
            .productId("prod_abc")
            .imageId("img_ubuntu2204")
            .hostname("web-01")
            .build());
        Server server = task.waitForCompletion();
    }
}
```

## Features

- All 105 API v2 endpoints
- Java 17+ with records and sealed types
- Immutable request/response objects
- Automatic pagination
- Rate limit handling with retry
- API key authentication

## Documentation

- [API Reference](https://data-mammoth.com/api-docs/reference)
- [Getting Started Guide](https://data-mammoth.com/api-docs/guides)
- [Authentication](https://data-mammoth.com/api-docs/guides/authentication)

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## License

MIT — see [LICENSE](LICENSE).
