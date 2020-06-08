# An Entity Tree

## Add an entity to the tree
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"parentId":"xyz","data": {"name": "jone doe"}}' \
  http://localhost:8080/api/send-entity

## Get the entity by entity id
curl http://localhost:8080/api/read-entity/:id

## Get entry list
curl http://localhost:8080/api/list-entries
