# Reblue

Reblue checks Coolblue for when items are available again. As soon as it notices that something becomes available, it will send you an email.

Configuration happens in [./items.edn].

## Installation

Download from https://github.com/wunki/reblue

## Usage

Run the project directly:

    $ clojure -M -m petar.dev.reblue

Run the project's tests:

    $ clojure -M:test:runner

Build an uberjar:

    $ clojure -M:uberjar

Run that uberjar:

    $ java -jar reblue.jar

## License

Copyright © 2020 Petar Radosevic

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
