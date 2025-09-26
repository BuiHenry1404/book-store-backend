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

        this.init();
    }

    init() {
        this.refreshBtn.addEventListener('click', () => this.loadBooks());
        this.searchBtn.addEventListener('click', () => this.searchBooks());
        this.clearBtn.addEventListener('click', () => this.clearSearch());
        this.searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.searchBooks();
            }
        });

        this.loadBooks();
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
        const searchTerm = this.searchInput.value.trim();
        if (!searchTerm) {
            this.loadBooks();
            return;
        }

        try {
            this.showLoading();
            this.hideError();
            this.hideEmptyState();

            const response = await fetch(`${API_BASE_URL}/books/title/${encodeURIComponent(searchTerm)}`);
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
