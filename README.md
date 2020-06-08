<img src="https://www.artipie.com/logo.svg" width="64px" height="64px"/>

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/artipie/pypi-adapter)](http://www.rultor.com/p/artipie/pypi-adapter)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Build Status](https://img.shields.io/travis/artipie/pypi-adapter/master.svg)](https://travis-ci.org/artipie/pypi-adapter)
[![Javadoc](http://www.javadoc.io/badge/com.artipie/pypi-adapter.svg)](http://www.javadoc.io/doc/com.artipie/pypi-adapter)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/artipie/pypi-adapter/blob/master/LICENSE.txt)
[![Hits-of-Code](https://hitsofcode.com/github/artipie/pypi-adapter)](https://hitsofcode.com/view/github/artipie/pypi-adapter)
[![Maven Central](https://img.shields.io/maven-central/v/com.artipie/pypi-adapter.svg)](https://maven-badges.herokuapp.com/maven-central/com.artipie/npm-adapter)

## PYPI repository API and file structure

PYPI repository API is explained by [PEP-503](https://www.python.org/dev/peps/pep-0503/).
The repository root `/` API must return a valid HTML5 page with a single anchor element per project in the repository:
```html
<!DOCTYPE html>
<html>
  <body>
    <a href="/frob/">frob</a>
    <a href="/spamspamspam/">spamspamspam</a>
  </body>
</html>
```

These links may be helpful:
 - Simple repository layout https://packaging.python.org/guides/hosting-your-own-index/
 - Repository API https://www.python.org/dev/peps/pep-0503/

## How to contribute

Fork repository, make changes, send us a pull request. We will review
your changes and apply them to the `master` branch shortly, provided
they don't violate our quality standards. To avoid frustration, before
sending us your pull request please run full Maven build:

```
$ mvn clean install -Pqulice
```

To avoid build errors use Maven 3.2+.

## How to use

For uploading using pip one must create a valid ~/.pypirc file:

```
[distutils]
index-servers = example

[example]
repository = https://example.com/pypi
username = myname
password = mypass
```

For installing packages one needs to add the following section to .pip/pip.conf

```
[global]
extra-index-url = https://myname:mypass@example.com/pypi/simple
```

For using a private server with setuptools unittesting you need to add the following to your setup.py:

```
from setuptools import setup

setup(
    ...
    dependency_links=[
        'https://myname:mypass@example.com/pypi/packages/'
    ])
```

You can find sample files in the [test resources](https://github.com/artipie/pypi-adapter/tree/master/src/test/resources/simple-pypi-project)