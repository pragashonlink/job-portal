## Publish job advert API specification

Base URL for the request to the API

> https://api.example.com/api

HTTP Verb

> POST

Authentication

> The publish job advert is authenticated using the API_KEY. These are unique tokens provided upon registration.
>> Authorization: Bearer {API_KEY}

Versioning

> Must be specified in the header X-API-Version: {VERSION_NO}

Content Type

> application/json

Responses

The response to a request will contain either an error response or a payload in the content type that the endpoint accepts.

| Field         | Type    |  Availability     |  Content                 |
| ------------- | ------- | ----------------- | ------------------------ |
| errorCode     | number  | Always present    | A unique error code      |
| errorSource   | string  | Sometimes present | A human-readable message |
| errorReason   | string  | Always present    | A human-readable message |

Idempotent

> The POST end points are not idempotent

Rate Limiting and Quotas

> The create post end points are rate limited. A client can submit 100 requests per day.

Open API Speicification

> [Open API Specification](./open-api-specification.json)