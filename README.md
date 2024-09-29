## UseCase Diagram
![usecase-diagram](./resources/usecase-diagram.png)

## Highlevel Architecture Diagram
![architecture-diagram](./resources/architecture-diagram.png)

## Diagram in words
External HRM System: Connects via secure API to an API Gateway.
API Gateway: Manages security (OAuth2, API Keys, etc.) and directs traffic to microservices like Job Publishing Service.
Job Publishing Service: Validates and processes job postings, then stores them in the Job Database.
Job Listing Service: Pulls job data from indexed database and serves it to the Job Portal UI for job seekers and employers. Handles complex job searches and filtering.
Application Service: Keeps track of all the applications that are submitted for a Job advert by job seekers.
Finance Service: All transaction details are stored here which can then be used to make financial reports.
Notification Service: Sending emails and alerts are done here

## Monitoring, Logging and Alerting
Logs: All the service's logs are aggregated and stored in a indexed database so that developers, support teams and search and look through logs. The logs include application logs, network logs, etc...
Monitoring: All the service health and metrics is pulled from the services and stored in a time series database to check health of services
Alerts: Alert the right channels when some anomalies happen in the system
