## UseCase Diagram
![usecase-diagram](./resources/usecase-diagram.png)

## Highlevel Architecture Diagram
![architecture-diagram](./resources/architecture-diagram.png)

## Diagram in words
- External HRM System: 
    - External HRM systems that are used by clients to manage job listings.
    - These systems communicate with the Job Portal via secure APIs to publish jobs.
- API Gateway: 
    - Central entry point for all API requests.
    - Handles requests from external HRM systems to publish jobs.
    - Handles API key validation, rate limiting, and security (OAuth2, JWT, or API Key-based authentication).
- Client Registration Service:
    - Handle client registration
    - API KEY generation and rotation
    - Manage profile
- Job Publishing Service: 
    - Validates and processes job postings, then stores them in the Job Database.
- Job Listing Service: 
    - Pulls job data from indexed database and serves it to the Job Portal UI for job seekers and employers. Handles complex job searches and filtering.
- Application Service: 
    - Keeps track of all the applications that are submitted for a Job advert by job seekers.
- Finance Service: 
    - All transaction details are stored here which can then be used to make financial reports.
- Notification Service: 
    - Sending emails and alerts are done here
- Authentication Service:
    - Authentication service supports and oAuth/OIDC

## Monitoring, Logging and Alerting
- Logs: All the service's logs are aggregated and stored in a indexed database so that developers, support teams and search and look through logs. The logs include application logs, network logs, etc...
- Monitoring: All the service health and metrics is pulled from the services and stored in a time series database to check health of services
- Alerts: Alert the right channels when some anomalies happen in the system

## Protocols
- All API communication happens over HTTPS
- The job posting end point is secured using API Keys
- The protected end points are authenticated using oAuth/OIDC
- Event broker is used to handle asynchronous communication between services

## How does the employers/clients create an account?
![sequence-diagram-client-registration](./client-registration-service/client-registration.png)

## How does the employers/clients publish a job advert?
![sequence-diagram-publish-job-advert](./job_publish-service/publish-job.png)

[Publish Job Advert API Specification](./job_publish-service/README.md)

## How does the applicant apply for a job advert?
![sequence-diagram-job-application](./application-service/job-application.png)

## Generate commission report?
![sequence-diagram-generate-commission-report](./financial-service/commission-report.png)


