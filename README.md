# Banxxy

[![DeepSource](https://deepsource.io/gh/Minibuz/Banxxy.svg/?label=active+issues&show_trend=true&token=kMNjgwCkMXxClRFOHIv8KG_f)](https://deepsource.io/gh/Minibuz/Banxxy/?ref=repository-badge)
[![DeepSource](https://deepsource.io/gh/Minibuz/Banxxy.svg/?label=resolved+issues&show_trend=true&token=kMNjgwCkMXxClRFOHIv8KG_f)](https://deepsource.io/gh/Minibuz/Banxxy/?ref=repository-badge)


This is a front-end/back-end application for a bank that allows customers and advisors to perform basic operations such as creating new transactions and viewing their transaction history. The front-end is built with Vue and the back-end is built with Java, and the database is run with Docker.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- [Vue](https://vuejs.org/)
- [Java](https://www.java.com/en/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)

### Installing

1. Clone the repository:
```bash
   git clone https://github.com/Minibuz/Banxxy.git
```

2. Navigate to the directory:
```bash
   cd Banxxy/frontend
```

3. Install the dependencies:
```bash
   npm install
```

4. Start the front-end development server:
```bash
   npm run serve
```

5. In a separate terminal, start the back-end server:
```bash
   cd backend
   mvn spring-boot:run
```

6. Navigate back to the root:
```bash
   cd ..
```

7. Run the Docker container for the database:
```bash
   docker-compose up
```

8. The application should now be running at http://localhost:8080.

## Usage

Customers and advisors can log in to the application with their respective credentials. From the dashboard, they can perform the following actions:

- Customers:
    - View their transaction history
    - Create new transactions
- Advisors:
    - View transaction histories for all customers
    - Create new transactions on behalf of customers

## License

This project is released under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Acknowledgments

- [Vue](https://vuejs.org/)
- [Java](https://www.java.com/en/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)