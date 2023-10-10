# SAT Results Management System

This is a web application built with Spring Boot for the backend and React for the frontend, designed to manage SAT results. It allows you to insert, view, rank, update, and delete SAT result records.

## Features

- Insert SAT result records with name, address, city, country, pincode, and score.
- Automatically calculate the pass/fail status based on the SAT score.
- View all SAT result records in JSON format.
- Get the rank of a user by name.
- Update SAT scores for existing users.
- Delete user records by name.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/sat-results-app.git

### Usage
# Access the application by navigating to http://localhost:3000 in your web browser.
# Use the menu to insert data, view all data, get ranks, update scores, and delete records.

### The backend provides the following API endpoints:
    # POST /satResult/addUser: Insert SAT result records.
    # GET /satResult/getAllUser: View all SAT result records.
    # GET /satResult/getRanking/{userName}: Get the rank of a user by name.
    # PUT /satResult/updateScore: Update SAT scores.
    # DELETE /satResult/deleteUser/{userName}: Delete user records by name.


