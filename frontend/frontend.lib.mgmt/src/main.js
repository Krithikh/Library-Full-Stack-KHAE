import './style.css'

const modules = [
  { name: 'Department', base: '/rest/departments', sampleId: 2 },
  { name: 'Member', base: '/rest/members', sampleId: 2 },
  { name: 'Author', base: '/rest/authors', sampleId: 2 },
  { name: 'Category', base: '/rest/categories', sampleId: 2 },
  { name: 'Publisher', base: '/rest/publishers', sampleId: 2 },
  { name: 'Book', base: '/rest/books', sampleId: 2 },
  { name: 'BookCopy', base: '/rest/book-copies', sampleId: 1 },
  { name: 'Membership', base: '/rest/memberships', sampleId: 2 },
  { name: 'BookIssue', base: '/rest/issues', sampleId: 1 },
  { name: 'BookReturn', base: '/rest/returns', sampleId: 1 },
  { name: 'Reservation', base: '/rest/reservations', sampleId: 1 },
  { name: 'Fine', base: '/rest/fines', sampleId: 1 }
]

document.querySelector('#app').innerHTML = `
  <header><h1>Library Full Stack - Presenter Baseline</h1><p>Vite frontend -> /rest proxy -> Spring Boot backend. The baseline REST services are presenter STUBs so every page can be exercised before student services are integrated.</p></header>
  <main>
    <section class="panel">
      <label>Module <select id="moduleSelect"></select></label>
      <label>ID <input id="recordId" value="2" /></label>
      <label>Search text <input id="searchText" value="CSE" /></label>
      <div class="actions"><button id="readButton">Read by ID</button><button id="searchButton">Search</button><a href="http://localhost:8080/library" target="_blank">Open Thymeleaf Backend Pages</a></div>
    </section>
    <section class="panel"><h2>Endpoint Patterns</h2><pre id="endpointOutput"></pre></section>
    <section class="panel"><h2>Response</h2><pre id="responseOutput">Choose a module and call an endpoint.</pre></section>
  </main>`

const moduleSelect = document.querySelector('#moduleSelect')
const recordId = document.querySelector('#recordId')
const searchText = document.querySelector('#searchText')
const endpointOutput = document.querySelector('#endpointOutput')
const responseOutput = document.querySelector('#responseOutput')

for (const module of modules) {
  const option = document.createElement('option')
  option.value = module.base
  option.textContent = module.name
  option.dataset.sampleId = module.sampleId
  moduleSelect.appendChild(option)
}

moduleSelect.addEventListener('change', showEndpoints)
document.querySelector('#readButton').addEventListener('click', handleRead)
document.querySelector('#searchButton').addEventListener('click', handleSearch)
showEndpoints()

function currentModule() {
  const selected = moduleSelect.selectedOptions[0]
  return { name: selected.textContent, base: selected.value, sampleId: selected.dataset.sampleId }
}

function showEndpoints() {
  const module = currentModule()
  recordId.value = module.sampleId
  endpointOutput.textContent = [
    `POST   ${module.base}`,
    `GET    ${module.base}/:id`,
    `PUT    ${module.base}/:id`,
    `DELETE ${module.base}/:id`,
    `GET    ${module.base}/search?text=...`
  ].join('\n')
}

async function handleRead() {
  await callApi(`${currentModule().base}/${recordId.value.trim()}`)
}

async function handleSearch() {
  const text = encodeURIComponent(searchText.value)
  await callApi(`${currentModule().base}/search?text=${text}`)
}

async function callApi(url) {
  responseOutput.textContent = `Calling ${url} ...`
  try {
    const response = await fetch(url)
    const body = await response.json()
    responseOutput.textContent = JSON.stringify(body, null, 2)
  } catch (error) {
    responseOutput.textContent = `Unable to call backend: ${error.message}`
  }
}
