"use strict";

const API_URL = "/api/categories";

const form = document.querySelector('category-form');
const categoryIdInput = document.querySelector('category-id');
const nameInput = document.querySelector('category-name');
const tableBody = document.querySelector('category-table-body');

console.log('Category JavaScript loaded');

async function loadCategories() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("could not be loaded.");
        }
        const data = await response.json();

        renderCategories(data);

    } catch (error) {
        console.error(error);
        showMessage("could not be loaded.");
    }
}

function renderCategories(categories) {
    tableBody.innerHTML = "";
    if(categories.length === 0){
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.textContent = "Categories not found";
    
        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }

    for (let category of categories) {
        const row = document.createElement("tr");
        const idCell = document.createElement("td");
        idCell.textContent = category.id;
        const nameCell = document.createElement("td");
        nameCell.textContent = category.name;
        const editBtn = document.createElement("edit-button");
        editBtn.type = 'button';
        editBtn.textContent = "Edit";
        editBtn.addEventListener('click', () => {
            startEdit(category);
        })
        const deleteBtn = document.createElement("delete-button");
        
        deleteBtn.textContent = "Delete";
        row.appendChild(idCell);
    }
    tableBody.appendChild(row);
    row.appendChild(tbody);
}

form.addEventListener(
    "submit",
    handleSubmit
);

async function handleSubmit(event) {
    event.preventDefault();
    const categoryName = nameInput.value;
    const category = {
        name: categoryName
    };

    await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(category),
    }
    );
    form.addEventListener("submit",
        handleSubmit
    )};

   loadCategories();
