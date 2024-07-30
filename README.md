# Annuaire des Établissements

Ce projet est une application web et mobile qui permet de rechercher et de visualiser les établissements de l'association Alfa3a. L'application affiche des informations détaillées sur chaque établissement, y compris l'adresse, le téléphone, les services offerts, les horaires et la distance depuis le siège social.

## Fonctionnalités

- Rechercher des établissements par nom, adresse, localité ou service.
- Filtrer les établissements par localité et par service.
- Afficher les horaires d'ouverture des établissements.
- Afficher la distance depuis le siège social d'Alfa3a.
- Conserver l'historique des recherches.
- Interface responsive adaptée aux appareils mobiles.

## Technologies utilisées

- HTML5
- CSS3 (avec [Tailwind CSS](https://tailwindcss.com/))
- JavaScript
- [Font Awesome](https://fontawesome.com/) pour les icônes
- [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) pour récupérer les données
- [Local Storage](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage) pour stocker l'historique des recherches

## Utilisation

### Recherche

- Saisissez le nom d'un établissement, une adresse, une localité ou un service dans le champ de recherche.
- Utilisez les filtres pour affiner votre recherche par localité ou service.

### Historique des recherches

- L'historique des recherches est affiché sous la barre de recherche.
- Cliquez sur le menu burger pour afficher l'historique des recherches.
- Cliquez sur un élément de l'historique pour effectuer à nouveau cette recherche.
- Cliquez sur "Effacer l'historique" pour supprimer l'historique des recherches.

### Distance depuis le siège social

- La distance entre chaque établissement et le siège social d'Alfa3a est calculée et affichée.

## Développement

### Ajouter un nouvel établissement

Pour ajouter un nouvel établissement, modifiez le fichier `updated_establishments.json` et ajoutez un nouvel objet JSON avec les informations de l'établissement.

### Ajouter des horaires

Pour ajouter ou modifier les horaires d'un établissement, modifiez le fichier `establishments_hours.json`.

