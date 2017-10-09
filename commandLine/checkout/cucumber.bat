@echo off
REM Comple CheckoutSteps
javac -cp "jars/*;." step_definitions/CheckoutSteps.java implementations/Checkout.java
REM Invoke Cucumber, adding the current directory (.) to the class path
REM Adding -g setp_definitions to tell where to look for step definitions
REM java -cp "jars/*;." cucumber.api.cli.Main -p pretty --snippets camelcase -g step_definitions features
REM
REM Changing pretty to progress for different output
java -cp "jars/*;." cucumber.api.cli.Main -p progress --snippets camelcase -g step_definitions features
