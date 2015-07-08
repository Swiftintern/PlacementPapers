# PlacementPapers
An Android App to help crack interviews of top companies

## Features
- Sharing Placement Paper or Interview Experience Company/Organization wise
- Add Placement Paper, Share Internship Experience
- Save Offline for later browsing

## API Request URL ##
### Fetching Random Companies with paper ###
GET Request parameter
- limit (number, the number of results per page)
- page (number. pagination based on limit per page)
```
http://swiftintern.com/organizations/placementpapers.json
```

### Searching Companies ###
GET Request parameter
- name (text, the name of company to search)
- limit (number, the number of results per page)
- page (number. pagination based on limit per page)
```
http://swiftintern.com/organizations.json
```

### Fetching Photo ###
Make request to `http://swiftintern.com/organizations/photo/{organization_id}`

### Fetching Company papers and Details ###
```
http://swiftintern.com/organization/detail/{organization_id}.json
```
### Registering Student  ###
POST Request parameter
- name (required)
- email (required)
- phone (optional)
- city (optional)
Returns 
"success": true //when request sucessful
"success": false //when request not sucessful

```
http://swiftintern.com/app/student.json
```
### Adding User Experience  ###
POST Request parameter
- title (title of paper)
- details (details of paper)
- user_id (user id of the person using app)
Returns 
"success": true //when request sucessful
"success": false //when request not sucessful

```
http://swiftintern.com/organizations/saveExperience/{organization_id}.json
```
