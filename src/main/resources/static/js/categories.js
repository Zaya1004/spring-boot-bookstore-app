"use strict";

const API_URL = "/api/categories";

const form = document.querySelector("#category-form");
const categoryIdInput = document.querySelector("#category-id");
const nameInput = document.querySelector("#category-name");
const tableBody = document.querySelector("#category-table-body");
const message = document.querySelector("#message");

console.log('Category JavaScript loaded');

async function loadCategories() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Could not be loaded.");
        }
        const data = await response.json();

        renderCategories(data);

    } catch (error) {
        console.error(error);
        showMessage("Could not be loaded.");
    }
}

function renderCategories(categories) {
    tableBody.innerHTML = "";
    if(categories.length === 0){
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.colSpan = 3;
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
        const actionCell = document.createElement('td');

        // Use standard button elements
        const editBtn = document.createElement("edit-button");
        editBtn.type = 'button';
        editBtn.textContent = "Edit";
        editBtn.addEventListener('click', () => {
            startEdit(category);
        })
        const deleteBtn = document.createElement("delete-button");
        deleteBtn.type = 'button';
        deleteBtn.textContent = "Delete";
        deleteBtn.addEventListener('click', () => {
            deleteCategory(category.id);
        })

        actionCell.appendChild(editBtn);
        actionCell.appendChild(deleteBtn); 
        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(actionCell);

        tableBody.appendChild(row);
    }
}

async function deleteCategory(id){
    const confirmed = confirm("Are you sure, you want to delete the Category?");
    if (!confirmed){
        return;
    }
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });
        if (!response.ok){
            throw new Error("Delete failed");
        }
        showMessage("Category deleted successfully");

        resetForm();
        await loadCategories();
    } catch (error) {
        console.error(error);
        showMessage("Delete failed");
    }
}

form.addEventListener(
    "submit",
    handleSubmit
);

async function handleSubmit(event) {
    event.preventDefault();
    const categoryName = nameInput.value.trim();
    const id = categoryIdInput ? categoryIdInput.value.trim() : "";

    const category = {
        name: categoryName
    };

    
    console.log('category', category)
    if(category.name === ""){
        showMessage("Name required");
        return;
    }

    const isEditing = id !== "";

    const url = isEditing ? `${API_URL}/${id}` : API_URL;
    
    const method = isEditing ? 'PUT' : 'POST';

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(category)
        });
        if (!response.ok){
            throw new Error("Request failed");
        }
        if(isEditing){
            showMessage("Updated successfully.")
        } else {
            showMessage("Created successfully.")
        }

        resetForm();
        await loadCategories();
    } catch (error){
        console.error(error);
        showMessage("Request failed");
    }
}

function resetForm(){
    form.reset();
    nameInput.value = "";
}

function startEdit(category){
    categoryIdInput.value = category.id;
    nameInput.value = category.name;
}

function showMessage(text){
    message.textContent = text;
    message.hidden = false;
    setTimeout(() => {
        message.hidden = true;
    }, 2000)
}

loadCategories();