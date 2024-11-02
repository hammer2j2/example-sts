# example-sts

An example Springboot 2 app calling AWS STS AssumeRole.

It runs on http://localhost:8080 

To test sts call http://localhost:8080/status/tests/sts

Assumes 
1. you have valid AWS credentials which the AWS DefaultCredentialsProvider can locate.
2. those credentials have permissions to assume a provided role
3. you provide the role arn to assume in an environment variable

Configure:

Requires setting the environment variable: `AWS_ROLE_ARN`

- Export the environment variable or provide on the command line, such as:

    `export AWS_ROLE_ARN="arn:aws:iam::123412341234:role/myrole"`

Build:

mvn package

Run:

java -jar `<path to jar>`

Example:

java -jar target/example-sts-0.0.1-SNAPSHOT.jar


