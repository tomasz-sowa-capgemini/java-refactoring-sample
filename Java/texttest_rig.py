#!/usr/bin/env python
"""
This script uses Gradle to execute the TexttestFixture.
It is designed to be used by TextTest and specified in the file 'texttests/config.gr' in this repo.
It is more convenient for TextTest to use since Gradle needs
several arguments in addition to the one the TextTest fixture needs.
"""

import os
import subprocess
import sys


def main() -> None:
    """Execute the TexttestFixture using Gradle."""
    args = " ".join(sys.argv[1:])
    texttest_home = os.environ.get("TEXTTEST_HOME", os.getcwd())

    gradle_command = (
        f"{texttest_home}/Java/gradlew "
        f"-p {texttest_home}/Java -q text --args {args}"
    )

    subprocess.run(gradle_command, shell=True, check=False)


if __name__ == "__main__":
    main()