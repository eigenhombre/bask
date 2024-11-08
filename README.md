# bask

<img src="https://github.com/eigenhombre/bask/blob/0cd39476c475bdd3c44e4ab845e0b512c7ade93f/bask.jpg" width="700">

A minimal parallel task runner.


## Status

I use this heavily in my day-to-day work, but it has not been
battle-tested by anyone else.  YMMV.

## Usage

Provide tasks to `bask` on stdin, as newline-separated commands.
Comments starting with `#` are ignored.  Pipes and redirects are
currently not supported.

Unless `-v` (or `--verbose`) is given, `bask` only prints `stdout` and
`stderr` from the first task that fails, if any.  The verbose flag
results in all output being printed.

Example:

    $ make install   # Assumes ~/bin is on your path, or $BINDIR defined
    $ bask -h
        -v, --verbose    Show verbose output
        -f, --input-file File with list of tasks
    $ cat example-tasks
    echo This is a task
    sleep 3
    sleep 1
    sleep 5
    echo This is another task

    $ time bask < example-tasks
    # ... takes awhile, shows spinners for each task until done ....
    Running 'echo This is a task'... ✓
    Running 'sleep 3'... ✓
    Running 'sleep 1'... ✓
    Running 'sleep 5'... ✓
    Running 'echo This is another task'... ✓

    real	0m5.804s
    user	0m0.038s
    sys	0m0.052s
    $

Alternative syntax:

    $ bask -f example-tasks

# Implementation

A single [Babashka](https://babashka.org/) script.

# License

Copyright (c) 2024 John Jacobsen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
