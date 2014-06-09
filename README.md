valtech-social-cli
==================

# Introduction
This is a small demo application, created during the recruiting process of Valtech. It is a really simple CLI
driven social media application.

Although the application uses Hibernate and HSQLDB behind the scenes, there is no real persistence (it was
simply not part of the task). But it shouldn't be that hard to let the posts and follower information survive
an application restart.
Also be aware of the fact, that the application doesn't care of concurrent access at the moment (again, there was
no need to implement it for the given scenarios). So, if you want to extend this example, ensure to synchronize the
appropriate stuff.

# Prerequisites
To get this application up and running, you need the following ingredients:
* Java 8
* Maven (tested with version 3.2.1)

# Build status
* Master branch: [![Build Status](https://travis-ci.org/daincredibleholg/valtech-social-cli.svg?branch=master)](https://travis-ci.org/daincredibleholg/valtech-social-cli)
* Develop branch: [![Build Status](https://travis-ci.org/daincredibleholg/valtech-social-cli.svg?branch=develop)](https://travis-ci.org/daincredibleholg/valtech-social-cli)

# How to start
Simply clone the repository and run the following command:
```
mvn clean install exec:java
```

# Usage
The following commands are available:

| What                 | Syntax                        | Example               |
|----------------------|-------------------------------|-----------------------|
| Show user's timeline | [username]                    | Alice                 |
| Show user's wall     | [username] wall               | Bob wall              |
| Post                 | [username] -> [message]       | Bob -> Oh, we lost!   |
| Follow a user        | [username] follows [followee] | Alice follows Charlie |
| Quit the application | quit                          | quit                  |

# Examples

## Posting
Users can publish posts to a personal timeline. Look at the following examples:
```
> Alice -> I love the weather today
> Bob -> Oh, we lost!
> Bob -> at least it's sunny
```

## Reading
To see published posts one can request a user's timeline. Based on the example above, 
Bob's timeline will look like this:
```
> Bob
at least it's sunny (1 minute ago)
Oh, we lost! (2 minutes ago)
```

## Following and the user wall
Like on Twitter, users can follow each other. Once a user is following another, the user will see the posts of the 
followee on his wall, in addition to his own posts. In the following example, Alice follows Bob and requests her wall:
```
> Alice follows Bob
> Alice wall
Bob - at least it's sunny (1 minute ago)
Bob - Oh, we lost! (2 minutes ago)
Alice - I love the weather today (1 hour ago)
```

# Some technical infos
The following libraries are used by this code:
* Hibernate (plus Log4j and SLF4j to configure Hibernate's logging facilities)
* HSQLDB
* PrettyTime
* JUnit

In addition, you will find code following these design patterns:
* Command
* Singleton
* Adapter
