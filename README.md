# PlacementPapers
An Android App with all placement papers

## Features
- Sharing Placement Paper or Interview Experience Company/Organization wise
- Upvote/Downvote Paper
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
