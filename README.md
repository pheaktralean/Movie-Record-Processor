# Movie Record Processor

This project processes movie records by categorizing them as **good** or **bad**, based on certain criteria. 
After categorization, the movies are further organized by their genres. 
The **good** movies are serialized and stored in their corresponding `genre.csv` files, 
while the **bad** movies are exported to a `bad.csv` file for further review.

## Features

- **Categorization**: Movies are filtered into two sections: good and bad.
- **Genre-based Organization**: Good movies are sorted into different genres and saved in their respective csv files.
- **CSV Export**: Bad movies are exported to a `bad.csv` file.
- **Serialization & Deserialization**: Good movie records are serialized for storage and then deserialized for future use.

## How It Works

1. **Categorization**: Each movie record is assessed based on certain criteria (e.g., ratings, reviews, etc.).
   - Filtering movies criteria based on semantic error, syntax error, or both.
   - Movies that meet the criteria are marked as **good**.
   - Movies that fail the criteria are marked as **bad**.

2. **Genre-based Processing**:
   - **Good Movies**: Movies that are categorized as good are further sorted by genre (e.g., action, comedy, drama).
     - Each genre is stored in a corresponding `.css` file (e.g., `action.css`, `comedy.css`).
   - **Bad Movies**: Movies that are categorized as bad are exported to `bad.csv` for future review.

3. **Serialization**: Good movies are serialized into a specific format before being saved to their genre-specific `.css` files.

4. **Deserialization**: Serialized movie records can be deserialized for easy retrieval and processing in the future.

## Technologies Used

- **Language**: Java
- **File Formats**: CSV for bad movies and good movies.
- **Serialization**: Java Serializable Interface
