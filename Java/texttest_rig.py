#!/usr/bin/env python
"""
This script uses Gradle to execute the TexttestFixture.

It is designed to be used by TextTest and specified in the file 'texttests/config.gr' in this repository.
It provides a convenient interface for TextTest since Gradle requires several arguments in addition to
the one needed by the TextTest fixture.
"""

import os
import subprocess
import sys


def run_texttest_fixture(arguments: list[str]) -> None:
    """
    Execute the TexttestFixture using Gradle.

    Args:
        arguments (list[str]): Command-line arguments passed to the script.
    """
    texttest_home = os.environ.get("TEXTTEST_HOME", os.getcwd())
    gradle_script = os.path.join(texttest_home, "Java", "gradlew")
    gradle_project_dir = os.path.join(texttest_home, "Java")

    command = [
        gradle_script,
        "-p", gradle_project_dir,
        "-q", "text",
        "--args", " ".join(arguments),
    ]

    subprocess.run(" ".join(command), shell=True, check=False)


def main() -> None:
    """Main entry point for the script."""
    run_texttest_fixture(sys.argv[1:])


if __name__ == "__main__":
    main()