# BlackRock Auto-Saving Retirement Challenge

## Submitted by Akhilesh Sahu

------------------------------------------------------------------------

## About Me

I am a Java Backend Developer with 3+ years of experience working on
enterprise-grade financial systems. My expertise includes:

-   Java, Spring Boot, Microservices Architecture
-   Kafka-based Event-Driven Systems
-   PostgreSQL, Redis
-   REST API Design (50+ production APIs delivered)
-   Performance Optimization & Scalable System Design

This solution reflects production-quality engineering standards with
performance, correctness, and scalability as primary goals.

------------------------------------------------------------------------

## Project Overview

This system implements an automated retirement savings engine that:

1.  Rounds each expense to the next multiple of 100
2.  Calculates the remanent (rounding difference)
3.  Applies temporal financial constraints (q, p, k rules)
4.  Calculates investment returns for:
    -   NPS (7.11% annual compound + tax benefit)
    -   Index Fund (14.49% annual compound)
5.  Adjusts final returns for inflation
6.  Provides performance metrics

The system is designed to handle up to 10\^6 transactions and period
rules efficiently.

------------------------------------------------------------------------

## Architecture Design

### Tech Stack

-   Java 17
-   Spring Boot
-   Maven
-   Docker
-   RESTful APIs

### Design Principles

-   Clean layered architecture (Controller → Service → Domain → Utility)
-   Stateless services
-   High-performance temporal filtering
-   Validation-first processing
-   Thread-safe computations
-   Memory optimized data handling

------------------------------------------------------------------------

## API Endpoints

### 1. Transaction Builder

POST /blackrock/challenge/v1/transactions:parse

Transforms raw expenses into enriched transactions with: - ceiling -
remanent

------------------------------------------------------------------------

### 2. Transaction Validator

POST /blackrock/challenge/v1/transactions:validator

Validates: - Negative amounts - Duplicate timestamps - Wage-based
constraints

Returns valid and invalid transaction lists.

------------------------------------------------------------------------

### 3. Temporal Constraints Processor

POST /blackrock/challenge/v1/transactions:filter

Applies business rules:

q Period: - Fixed override - Latest start date takes priority

p Period: - Extra addition - Multiple matches are cumulative

k Period: - Inclusive grouping - One transaction may belong to multiple
k ranges

------------------------------------------------------------------------

### 4. Returns Calculation

POST /blackrock/challenge/v1/returns:nps\
POST /blackrock/challenge/v1/returns:index

Calculates:

-   Total savings per k period
-   Compound returns until retirement (age 60)
-   Inflation-adjusted returns
-   Tax benefit (NPS only)

------------------------------------------------------------------------

## Financial Logic

### Compound Interest Formula

A = P × (1 + r)\^t

Where: P = invested amount\
r = annual rate\
t = (60 - age) years

### Inflation Adjustment

A_real = A / (1 + inflation)\^t

------------------------------------------------------------------------

## Tax Logic (NPS)

Eligible Deduction: min(invested, 10% of annual income, ₹2,00,000)

Tax Benefit: Tax(income) - Tax(income - deduction)

Tax benefit is returned separately and does not compound.

------------------------------------------------------------------------

## Performance Considerations

-   Efficient date comparisons
-   Optimized period matching
-   Single-pass transaction processing
-   Handles large-scale input (10\^6 constraints)
-   Low memory footprint

------------------------------------------------------------------------

## How to Run

### Build Application

mvn clean package

### Build Docker Image

docker build -t blk-hacking-ind-akhilesh-sahu .

### Run Container

docker run -d -p 5477:5477 blk-hacking-ind-akhilesh-sahu

Application runs at: http://localhost:5477

------------------------------------------------------------------------

## Docker Configuration

-   Runs on port 5477
-   Linux-based container
-   EXPOSE 5477 in Dockerfile

Image naming convention followed: blk-hacking-ind-akhilesh-sahu

------------------------------------------------------------------------

## Project Structure

src/ ├── controller/ ├── service/ ├── model/ ├── util/ └── test/

Dockerfile\
compose.yaml\
README.md

------------------------------------------------------------------------

## Assumptions

-   Timestamp format: YYYY-MM-DD HH:mm:ss
-   Date ranges are inclusive
-   Salary considered pre-tax
-   Inflation applied after compound return
-   Simplified tax slabs used as defined in problem statement

------------------------------------------------------------------------

## Innovation & Engineering Approach

This solution emphasizes:

-   Clean architecture
-   Financial correctness
-   Deterministic rule processing order
-   Performance scalability
-   Containerized deployment readiness

------------------------------------------------------------------------

## Submission Details

Public repository contains:

-   Complete source code
-   Dockerfile
-   Optional compose.yaml
-   Test cases
-   This README.md

------------------------------------------------------------------------

Thank you for reviewing my submission.
