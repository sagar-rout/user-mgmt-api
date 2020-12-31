# User Management API

This app provides API to maintain user management. It provides following features :

- User API : User Registration and CRUD on user information.
- Admin API : Manage users and other admin related stuff like assigning users to different groups.
- Authenticate API : Authenticate user with emailAddress and password and generate jwt based token.

## Development

API DOCS : OpenApi V3 http://localhost:8080/api-docs/

Database : PostgreSQL 12.5

```bash
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres:12.5
```

Logging Style:

* Console : Plain text logging
* Rolling File : Json based logging which we can integrate with ELK using filebeat or logstash agent.

Code Formatting Style : [Google Java code style](https://google.github.io/styleguide/javaguide.html)