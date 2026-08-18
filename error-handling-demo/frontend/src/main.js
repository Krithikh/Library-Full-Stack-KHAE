import './style.css'

const DEFAULT_API_URL = '/rest/demo/books'
const NETWORK_FAILURE_URL = 'http://localhost:65530/rest/demo/books'

const scenarios = {
  success: { label: 'Success - HTTP 201', description: 'A normal successful stub response.', payload: { title: 'Refactoring', accessionNumber: 'ACC-2101', author: 'Martin Fowler' } },
  'client-validation': { label: 'Client-side validation - missing title', description: 'Handled screen stops before the API call; unhandled screen sends the invalid request and receives HTTP 400.', payload: { title: '', accessionNumber: 'ACC-2102', author: 'Demo Author' } },
  'backend-validation': { label: 'Backend validation - HTTP 400', description: 'The accession number format is rejected by the stub backend.', payload: { title: 'Domain-Driven Design', accessionNumber: 'BAD-2103', author: 'Eric Evans' } },
  duplicate: { label: 'Business conflict - HTTP 409', description: 'The stub reserves ACC-0001 as an existing accession number.', payload: { title: 'Clean Code', accessionNumber: 'ACC-0001', author: 'Robert C. Martin' } },
  'session-expired': { label: 'Authentication failure - HTTP 401', description: 'The stub simulates an expired session.', payload: { title: 'SESSION EXPIRED', accessionNumber: 'ACC-4010', author: 'Demo User' } },
  forbidden: { label: 'Authorization failure - HTTP 403', description: 'The stub simulates a user who is authenticated but not authorized.', payload: { title: 'FORBIDDEN BOOK', accessionNumber: 'ACC-4030', author: 'Demo User' } },
  'not-found': { label: 'Not found - HTTP 404', description: 'The stub simulates a related Library record that cannot be found.', payload: { title: 'MISSING BOOK', accessionNumber: 'ACC-4040', author: 'Demo User' } },
  'server-error': { label: 'Unexpected backend failure - HTTP 500', description: 'The stub returns a controlled HTTP 500 response. No database is involved.', payload: { title: 'SERVER ERROR', accessionNumber: 'ACC-5000', author: 'Demo User' } },
  slow: { label: 'Slow response - loading state', description: 'The stub waits three seconds before returning HTTP 201.', payload: { title: 'SLOW BOOK', accessionNumber: 'ACC-3000', author: 'Demo User' } },
  network: { label: 'Network failure - no HTTP response', description: 'Both screens call the same intentionally unreachable URL so the browser raises a network error.', payload: { title: 'Network Demo Book', accessionNumber: 'ACC-9000', author: 'Demo User' }, apiUrl: NETWORK_FAILURE_URL }
}

const params = new URLSearchParams(window.location.search)
const initialMode = params.get('mode') === 'handled' ? 'handled' : 'without'
const initialScenario = scenarios[params.get('scenario')] ? params.get('scenario') : 'success'
const app = document.querySelector('#app')

app.innerHTML = `
<header class="topbar"><div><h1>KHAE Library Management</h1><p>Error Handling and User Feedback Demonstration - Stub Backend Only</p></div></header>
<main class="page-shell">
  <section class="intro-card">
    <div class="demo-note"><strong>Teaching architecture:</strong> Vite frontend + Spring Boot stub API. No database, JPA, JDBC, repository, datasource, or Flyway is used.</div>
    <h2>Compare the Same API Call With and Without Error Handling</h2>
    <p>Both screens use the same stub API contract. Choose a scenario, run it without handling, then run the same scenario on the handled screen.</p>
    <div class="scenario-picker">
      <label for="scenarioSelect"><strong>Scenario</strong></label>
      <select id="scenarioSelect">${Object.entries(scenarios).map(([key, value]) => `<option value="${key}">${value.label}</option>`).join('')}</select>
      <button id="loadScenario" type="button">Load Scenario</button>
    </div>
    <div id="scenarioDescription" class="scenario-description"></div>
    <div class="api-contract"><strong>Normal API endpoint used by both screens:</strong> <code>${DEFAULT_API_URL}</code></div>
  </section>
  <nav class="screen-tabs" aria-label="Demo screens">
    <button id="showWithout" class="tab">Screen 1: Without Error Handling</button>
    <button id="showWith" class="tab">Screen 2: With Error Handling</button>
  </nav>
  <section id="withoutScreen" class="demo-screen"></section>
  <section id="withScreen" class="demo-screen hidden"></section>
</main>`

const formTemplate = (mode) => `
<div class="screen-heading ${mode}">
  <div><span class="eyebrow">${mode === 'without' ? 'Poor Practice Demonstration' : 'Recommended Practice Demonstration'}</span><h2>${mode === 'without' ? 'Create Book Without Error Handling' : 'Create Book With Error Handling'}</h2></div>
  <span class="mode-badge">${mode === 'without' ? 'NO HANDLING' : 'HANDLED'}</span>
</div>
<div class="teaching-grid">
  <form id="${mode}Form" class="book-form" novalidate>
    <label>Book Title<input id="${mode}Title" name="title" /><small id="${mode}TitleError" class="field-error"></small></label>
    <label>Accession Number<input id="${mode}Accession" name="accessionNumber" /><small id="${mode}AccessionError" class="field-error"></small></label>
    <label>Author<input id="${mode}Author" name="author" /></label>
    <button id="${mode}Save" type="submit">Save Book</button>
    <div id="${mode}Status" class="status-area" role="status" aria-live="polite"></div>
  </form>
  <aside class="observation-panel"><h3>What Students Should Observe</h3><ul id="${mode}Observations"></ul><div class="developer-box"><strong>Developer Console</strong><p>Open browser developer tools and compare the technical result with what the librarian sees.</p></div></aside>
</div>`

document.querySelector('#withoutScreen').innerHTML = formTemplate('without')
document.querySelector('#withScreen').innerHTML = formTemplate('with')
document.querySelector('#withoutObservations').innerHTML = '<li>Invalid input is sent to the API.</li><li>No loading state and the Save button stays enabled.</li><li>HTTP status and field details are not translated for the user.</li><li>401, 403, 404, 409, 500 and network failures all become the same generic message.</li><li>The console contains more information than the visible screen.</li>'
document.querySelector('#withObservations').innerHTML = '<li>Required-field errors are caught before the API call.</li><li>The Save button is disabled while the request runs.</li><li>Backend field errors appear beside the correct field.</li><li>401, 403, 404, 409, 500 and network failures become meaningful messages.</li><li>The UI is restored in a finally block after success or failure.</li>'

function setQuery(changes) {
  const next = new URL(window.location.href)
  Object.entries(changes).forEach(([key, value]) => next.searchParams.set(key, value))
  window.history.replaceState({}, '', next)
}

function switchScreen(mode, updateUrl = true) {
  const handled = mode === 'handled'
  document.querySelector('#withoutScreen').classList.toggle('hidden', handled)
  document.querySelector('#withScreen').classList.toggle('hidden', !handled)
  document.querySelector('#showWithout').classList.toggle('active', !handled)
  document.querySelector('#showWith').classList.toggle('active', handled)
  if (updateUrl) setQuery({ mode })
}

document.querySelector('#showWithout').addEventListener('click', () => switchScreen('without'))
document.querySelector('#showWith').addEventListener('click', () => switchScreen('handled'))

function values(mode) {
  return {
    title: document.querySelector(`#${mode}Title`).value,
    accessionNumber: document.querySelector(`#${mode}Accession`).value,
    author: document.querySelector(`#${mode}Author`).value
  }
}

function clearHandledErrors() {
  ['withTitle', 'withAccession'].forEach(id => document.querySelector(`#${id}`)?.classList.remove('invalid'))
  if (document.querySelector('#withTitleError')) document.querySelector('#withTitleError').textContent = ''
  if (document.querySelector('#withAccessionError')) document.querySelector('#withAccessionError').textContent = ''
}

function applyScenario(key, updateUrl = true) {
  const scenario = scenarios[key] ?? scenarios.success
  document.querySelector('#scenarioSelect').value = key
  document.querySelector('#scenarioDescription').textContent = scenario.description
  for (const mode of ['without', 'with']) {
    document.querySelector(`#${mode}Title`).value = scenario.payload.title
    document.querySelector(`#${mode}Accession`).value = scenario.payload.accessionNumber
    document.querySelector(`#${mode}Author`).value = scenario.payload.author
    document.querySelector(`#${mode}Status`).textContent = ''
  }
  clearHandledErrors()
  if (updateUrl) setQuery({ scenario: key })
}

document.querySelector('#loadScenario').addEventListener('click', () => applyScenario(document.querySelector('#scenarioSelect').value))
document.querySelector('#scenarioSelect').addEventListener('change', e => applyScenario(e.target.value))

function currentApiUrl() {
  return scenarios[document.querySelector('#scenarioSelect').value].apiUrl ?? DEFAULT_API_URL
}

document.querySelector('#withoutForm').addEventListener('submit', async (event) => {
  event.preventDefault()
  const status = document.querySelector('#withoutStatus')
  status.className = 'status-area'
  status.textContent = ''
  try {
    const response = await fetch(currentApiUrl(), { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(values('without')) })
    if (!response.ok) {
      console.error('Raw stub API failure:', response.status, await response.text())
      status.className = 'status-area generic-error'
      status.textContent = 'Error'
      return
    }
    console.log('Stub response:', await response.json())
    status.textContent = 'Done'
  } catch (error) {
    console.error('Network failure:', error)
    status.className = 'status-area generic-error'
    status.textContent = 'Error'
  }
})

function showFieldError(field, message) {
  const mapping = { title: ['withTitle', 'withTitleError'], accessionNumber: ['withAccession', 'withAccessionError'] }
  const target = mapping[field]
  if (!target) return false
  document.querySelector(`#${target[0]}`).classList.add('invalid')
  document.querySelector(`#${target[1]}`).textContent = message
  return true
}

function validateHandledForm(payload) {
  let valid = true
  if (!payload.title.trim()) { showFieldError('title', 'Book title is required.'); valid = false }
  if (!payload.accessionNumber.trim()) { showFieldError('accessionNumber', 'Accession number is required.'); valid = false }
  return valid
}

function friendlyMessage(body, status) {
  if (body?.message) return body.message
  if (status === 400) return 'The Library request contains invalid data. Correct the highlighted values.'
  if (status === 401) return 'Your Library session has expired. Please sign in again.'
  if (status === 403) return 'You do not have permission to perform this Library operation.'
  if (status === 404) return 'The requested Library record was not found.'
  if (status === 409) return 'The Library request conflicts with an existing record.'
  if (status >= 500) return 'The Library server could not complete the request. Please try again later.'
  return 'The Library request could not be completed.'
}

document.querySelector('#withForm').addEventListener('submit', async (event) => {
  event.preventDefault()
  clearHandledErrors()
  const statusArea = document.querySelector('#withStatus')
  const saveButton = document.querySelector('#withSave')
  const payload = values('with')

  if (!validateHandledForm(payload)) {
    statusArea.className = 'status-area error-message'
    statusArea.textContent = 'Please correct the highlighted fields before saving.'
    return
  }

  saveButton.disabled = true
  saveButton.textContent = 'Saving...'
  statusArea.className = 'status-area loading-message'
  statusArea.innerHTML = '<span class="spinner" aria-hidden="true"></span> Saving the Book record. Please wait...'

  try {
    const response = await fetch(currentApiUrl(), { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(payload) })
    const body = await response.json().catch(() => ({}))
    if (!response.ok) {
      console.error('Create Book stub request failed:', { status: response.status, body })
      if (body.field && showFieldError(body.field, friendlyMessage(body, response.status))) {
        statusArea.className = 'status-area error-message'
        statusArea.textContent = 'The Book was not saved. Correct the highlighted field and try again.'
      } else {
        statusArea.className = 'status-area error-message'
        statusArea.textContent = friendlyMessage(body, response.status)
      }
      return
    }
    console.log('Create Book stub request succeeded:', body)
    statusArea.className = 'status-area success-message'
    statusArea.textContent = `Book saved successfully by the stub API. Demo record ID: ${body.id}.`
  } catch (error) {
    console.error('Create Book network failure:', error)
    statusArea.className = 'status-area error-message'
    statusArea.textContent = 'Unable to connect to the Library API. The request was not confirmed. Check the network or backend and try again.'
  } finally {
    saveButton.disabled = false
    saveButton.textContent = 'Save Book'
  }
})

applyScenario(initialScenario, false)
switchScreen(initialMode, false)
