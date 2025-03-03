# Changelog

## [Unreleased]
### Changes
- Renamed columns to have proper names.
- Fixed an issue with renaming the `comments` column due to an extra space at the end of the name.
- Checked for missing comments and dropped any missing values to ensure all comments are available for classification.
- Added a pie chart to the notebook to visually represent the distribution of comments categorized as negative, positive, neutral, and irrelevant.
- Converted `comments` and `emotional` columns into numerical data, as multinomial Naïve Bayes only processes numerical data, requiring string-based sentiment data to be transformed.

