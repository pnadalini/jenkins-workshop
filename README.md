# JENKINS WORKSHOP

This repository is used in the course from dev to devops, and it contains files that will bring up a jenkins server using a dockerfile and docker-compose.


## Setting up

As the instructor show you in the course, in order to run the jenkins server you need to execute

````
docker-compose build
docker-compose up
````

After that you need to wait a couple minutes meanwhile all the configurations are created.

## Homework

After you setup the container you will see that the job that was created by the seed-job is not working (jenkins_master_build), so you need to complete the following tasks to complete the homework.

- Fix the job, you may have to install dependencies in the Dockerfile.
- Update the Jenkinsfile to publish the container to an aws ECR.

There are no constraints in the above requirements, try to fix it in the best way possible.
