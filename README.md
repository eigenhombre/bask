# bask

<img src="https://user-images.githubusercontent.com/382668/200961230-63584dce-8811-4108-b3c6-b6ff30bafe5a.png" width="500">

A minimal parallel task runner.


# Status

I use this heavily in my day-to-day work, but it has not been
battle-tested by anyone else.  YMMV.

## Usage

Provide tasks to `bask` on stdin, as newline-separated commands.
Comments starting with `#` are ignored.  Pipes and redirects are
currently not supported.

Only prints `stdout` and `stderr` from the first task that
fails.

Example:

    $ make install   # Assumes ~/bin is on your path
    $ cat example-tasks
    echo This is a task
    sleep 3
    sleep 1
    sleep 5
    echo This is another task
    $ bask < example-tasks
    # ... takes awhile, shows spinners for each task until done ....
    Running 'echo This is a task'... ✓
    Running 'sleep 3'... ✓
    Running 'sleep 1'... ✓
    Running 'sleep 5'... ✓
    Running 'echo This is another task'... ✓
    $

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
