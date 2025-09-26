// Book Store Frontend JavaScript
const API_BASE_URL = 'http://localhost:8080/api/v1';

class BookStoreApp {
    constructor() {
        this.booksContainer = document.getElementById('booksContainer');
        this.loading = document.getElementById('loading');
        this.error = document.getElementById('error');
        this.emptyState = document.getElementById('emptyState');
        this.refreshBtn = document.getElementById('refreshBtn');
        this.searchInput = document.getElementById('searchInput');
        this.searchBtn = document.getElementById('searchBtn');
        this.clearBtn = document.getElementById('clearBtn');
        this.searchType = document.getElementById('searchType');
        this.categorySelect = document.getElementById('categorySelect');

        this.init();
    }

    init() {
        this.refreshBtn.addEventListener('click', () => this.loadBooks());
        this.searchBtn.addEventListener('click', () => this.searchBooks());
        this.clearBtn.addEventListener('click', () => this.clearSearch());
        this.searchType.addEventListener('change', () => this.handleSearchTypeChange());
        this.searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.searchBooks();
            }
        });

        this.loadBooks();
        this.loadCategories();
    }

    handleSearchTypeChange() {
        const searchType = this.searchType.value;
        if (searchType === 'category') {
            this.categorySelect.style.display = 'block';
            this.searchInput.style.display = 'none';
            this.searchInput.value = '';
        } else {
            this.categorySelect.style.display = 'none';
            this.searchInput.style.display = 'block';
            this.categorySelect.value = '';
        }
    }

    async loadCategories() {
        try {
            console.log('Loading categories from:', `${API_BASE_URL}/categories`);
            const response = await fetch(`${API_BASE_URL}/categories`);
            const categories = await response.json();

            console.log('Categories response status:', response.status);
            console.log('Categories response ok:', response.ok);
            console.log('Categories data:', categories);

            if (response.ok && categories && categories.length > 0) {
                console.log('Populating category dropdown with', categories.length, 'categories');
                this.categorySelect.innerHTML = '<option value="">Select Category...</option>';
                categories.forEach((category, index) => {
                    console.log(`Category ${index}:`, category);
                    const option = document.createElement('option');
                    option.value = category.id; // Changed from category.idCategory to category.id
                    option.textContent = category.nameCategory;
                    this.categorySelect.appendChild(option);
                });
                console.log('Category dropdown populated successfully');
            } else {
                console.error('Failed to load categories:', { response, categories });
            }
        } catch (error) {
            console.error('Error loading categories:', error);
        }
    }

    async loadBooks() {
        try {
            this.showLoading();
            this.hideError();
            this.hideEmptyState();

            const response = await fetch(`${API_BASE_URL}/books`);
            const books = await response.json();

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            this.displayBooks(books);
        } catch (error) {
            console.error('Error loading books:', error);
            this.showError();
        }
    }

    async searchBooks() {
        const searchType = this.searchType.value;
        let searchTerm = '';
        let searchValue = '';

        if (searchType === 'category') {
            searchValue = this.categorySelect.value;
            console.log('Category search - selected value:', searchValue);
            console.log('Category select element:', this.categorySelect);
            console.log('Category select options:', this.categorySelect.options);

            // Check if no category is selected or if value is "undefined" or empty
            if (!searchValue || searchValue === "" || searchValue === "undefined" || searchValue === "Select Category...") {
                console.log('No valid category selected, loading all books');
                this.loadBooks();
                return;
            }

            // Convert to number to ensure it's a valid integer
            const categoryId = parseInt(searchValue, 10);
            if (isNaN(categoryId) || categoryId <= 0) {
                console.log('Invalid category ID:', categoryId);
                this.loadBooks();
                return;
            }

            searchValue = categoryId.toString();
            console.log('Using category ID:', searchValue);
        } else {
            searchTerm = this.searchInput.value.trim();
            if (!searchTerm) {
                this.loadBooks();
                return;
            }
            searchValue = encodeURIComponent(searchTerm);
        }

        try {
            this.showLoading();
            this.hideError();
            this.hideEmptyState();

            let url = '';
            if (searchType === 'title') {
                url = `${API_BASE_URL}/books/title/${searchValue}`;
            } else if (searchType === 'author') {
                url = `${API_BASE_URL}/books/author/${searchValue}`;
            } else if (searchType === 'category') {
                url = `${API_BASE_URL}/books/category/${searchValue}`;
                console.log('Category search URL:', url);
            }

            console.log('Making request to:', url);
            const response = await fetch(url);
            const books = await response.json();

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            this.displayBooks(books);
        } catch (error) {
            console.error('Error searching books:', error);
            this.showError();
        }
    }

    clearSearch() {
        this.searchInput.value = '';
        this.categorySelect.value = '';
        this.searchType.value = 'title';
        this.handleSearchTypeChange();
        this.loadBooks();
    }

    displayBooks(books) {
        if (!books || books.length === 0) {
            this.showEmptyState();
            return;
        }

        this.booksContainer.innerHTML = '';

        books.forEach((book, index) => {
            const bookCard = this.createBookCard(book, index);
            this.booksContainer.appendChild(bookCard);
        });

        this.hideLoading();
    }

    createBookCard(book, index) {
        const card = document.createElement('div');
        card.className = 'book-card';
        card.style.animationDelay = `${index * 0.1}s`;

        const imageUrl = `http://localhost:8080${book.imageUrl}`;

        card.innerHTML = `
            <img src="${imageUrl}" alt="${book.title}" class="book-image">
            <h3 class="book-title">${this.escapeHtml(book.title)}</h3>
            <p class="book-author">👤 ${this.escapeHtml(book.author)}</p>
            <p class="book-price">💰 $${book.price.toFixed(2)}</p>
            <div class="book-availability">
                <span class="${book.available ? 'available' : 'not-available'}">
                    ${book.available ? '✅ Available' : '❌ Not Available'}
                </span>
            </div>
            <p class="book-description">${this.escapeHtml(book.description || 'No description available')}</p>
            <div class="book-categories">
                ${book.categories && book.categories.length > 0 ? book.categories.map(category =>
                    `<span class="category-tag">${this.escapeHtml(category.nameCategory)}</span>`
                ).join('') : '<span class="category-tag">No categories</span>'}
            </div>
        `;

        return card;
    }

    escapeHtml(text) {
        if (!text) return '';
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    showLoading() {
        this.loading.style.display = 'flex';
        this.booksContainer.style.display = 'none';
    }

    hideLoading() {
        this.loading.style.display = 'none';
        this.booksContainer.style.display = 'grid';
    }

    showError() {
        this.error.style.display = 'block';
        this.loading.style.display = 'none';
        this.booksContainer.style.display = 'none';
    }

    hideError() {
        this.error.style.display = 'none';
    }

    showEmptyState() {
        this.emptyState.style.display = 'block';
        this.loading.style.display = 'none';
        this.booksContainer.style.display = 'none';
    }

    hideEmptyState() {
        this.emptyState.style.display = 'none';
    }
}

// Initialize the app when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    new BookStoreApp();
});

// Add some utility functions to the global scope for HTML onerror handlers
window.handleImageError = function(img) {
    img.src = 'https://via.placeholder.com/300x200/e0e0e0/666666?text=Image+Error';
};
