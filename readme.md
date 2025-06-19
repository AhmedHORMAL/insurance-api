
````md
### 📦 API Contract & Code Generation

This project uses [OpenAPI Generator](https://openapi-generator.tech/) to generate Java DTOs and API interfaces from an OpenAPI contract (`contract.yaml`).

#### ✅ What is generated?

- `com.tinubu.web.dto.*` → API models: `Policy`, `PolicyInput`
- `com.tinubu.web.api.*` → API interfaces: `PolicyApi`

---

#### 🛠 How to generate code

You must have [OpenAPI Generator CLI](https://openapi-generator.tech/docs/installation) installed:

```bash
brew install openapi-generator
# or using Docker:
# docker pull openapitools/openapi-generator-cli
````

Then run:

```bash
openapi-generator generate \
  -i contract.yaml \
  -g spring \
  -o generated \
  --additional-properties=interfaceOnly=true,modelPackage=com.tinubu.web.dto,apiPackage=com.tinubu.web.api
```

This will generate files into `generated/src/main/java`.

---

#### 📁 What to do next

Manually copy the generated packages into your project:

```bash
cp -r generated/src/main/java/com/tinubu/web/dto src/main/java/com/tinubu/web/
cp -r generated/src/main/java/com/tinubu/web/api src/main/java/com/tinubu/web/
```

Then implement `PolicyApi` in your controller:

```java
@RestController
public class PolicyController implements PolicyApi {
  // implementation here
}
```

---

#### 🧠 Notes

* **Do not edit generated files manually** — always regenerate them from `contract.yaml`.
* The contract is the **single source of truth** for the public API.
* You can validate your contract via tools like [Swagger Editor](https://editor.swagger.io/).

```

Tu peux coller ce bloc tel quel dans ton `README.md`, il est directement compatible Markdown.

Souhaites-tu aussi une section `Makefile` ou `mvn plugin` pour automatiser la génération plus tard ?
```
