# Directory of Establishments

This project is a web and mobile application that allows searching and viewing the establishments of the Alfa3a association. The application displays detailed information about each establishment, including address, phone number, services offered, opening hours, and distance from the headquarters.

## Features

- Search for establishments by name, address, locality, or service.
- Filter establishments by locality and service.
- Display opening hours of establishments.
- Display distance from Alfa3a headquarters.
- Maintain a search history.
- Responsive interface suitable for mobile devices.

## Technologies Used

- HTML5
- CSS3 (with [Tailwind CSS](https://tailwindcss.com/))
- JavaScript
- [Font Awesome](https://fontawesome.com/) for icons
- [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) to fetch data
- [Local Storage](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage) to store search history

## Usage

### Search

- Enter the name of an establishment, address, locality, or service in the search field.
- Use the filters to refine your search by locality or service.

### Search History

- The search history is displayed below the search bar.
- Click on the burger menu to display the search history.
- Click on a history item to perform that search again.
- Click "Clear History" to delete the search history.

### Distance from Headquarters

- The distance between each establishment and the Alfa3a headquarters is calculated and displayed.

## Development

### Add a New Establishment

To add a new establishment, modify the `updated_establishments.json` file and add a new JSON object with the establishment's information.

### Add Opening Hours

To add or modify the opening hours of an establishment, modify the `establishments_hours.json` file.
