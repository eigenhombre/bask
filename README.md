# bask

A minimal parallel task runner.  Provide tasks as newline-separated
commands.  No pipes, comments, or anything fancy like that.

Only prints `stdout` and `stderr` from tasks that fail.

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

MIT
