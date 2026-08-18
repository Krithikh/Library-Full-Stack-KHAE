import './style.css'

const DEFAULT_API_URL = '/rest/demo/books'
const NETWORK_FAILURE_URL = 'http://localhost:65530/rest/demo/books'

const techniques = {
  'client-validation': {
    order: 1,
    icon: '✓',
    title: 'Client-Side Validation',
    subtitle: 'Stop invalid input before the request leaves the browser.',
    errorType: 'Missing required field',
    expected: 'Handled page highlights the field and sends no HTTP request.',
    payload: { title: '', accessionNumber: 'ACC-2102', author: 'Demo Author' }
  },
  'backend-validation': {
    order: 2,
    icon: '400',
    title: 'Backend Validation',
    subtitle: 'Translate a backend validation response into field-level feedback.',
    errorType: 'HTTP 400 Bad Request',
    expected: 'Handled page maps the backend field error to Accession Number.',
    payload: { title: 'Domain-Driven Design', accessionNumber: 'BAD-2103', author: 'Eric Evans' }
  },
  duplicate: {
    order: 3,
    icon: '409',
    title: 'Business Conflict Handling',
    subtitle: 'Explain a duplicate or business-rule conflict without clearing the form.',
    errorType: 'HTTP 409 Conflict',
    expected: 'Handled page highlights ACC-0001 and explains the duplicate.',
    payload: { title: 'Clean Code', accessionNumber: 'ACC-0001', author: 'Robert C. Martin' }
  },
  'session-expired': {
    order: 4,
    icon: '401',
    title: 'Authentication Error Handling',
    subtitle: 'Convert an expired-session response into a clear sign-in instruction.',
    errorType: 'HTTP 401 Unauthorized',
    expected: 'Handled page tells the user that the Library session expired.',
    payload: { title: 'SESSION EXPIRED', accessionNumber: 'ACC-4010', author: 'Demo User' }
  },
  forbidden: {
    order: 5,
    icon: '403',
    title: 'Authorization Error Handling',
    subtitle: 'Distinguish “not permitted” from “not authenticated”.',
    errorType: 'HTTP 403 Forbidden',
    expected: 'Handled page explains that permission is missing.',
    payload: { title: 'FORBIDDEN BOOK', accessionNumber: 'ACC-4030', author: 'Demo User' }
  },
  'not-found': {
    order: 6,
    icon: '404',
    title: 'Not-Found Handling',
    subtitle: 'Tell the user that a requested or related record cannot be found.',
    errorType: 'HTTP 404 Not Found',
    expected: 'Handled page shows a Library-specific not-found message.',
    payload: { title: 'MISSING BOOK', accessionNumber: 'ACC-4040', author: 'Demo User' }
  },
  'server-error': {
    order: 7,
    icon: '500',
    title: 'Unexpected Server Error Handling',
    subtitle: 'Hide technical exception details and show a safe message.',
    errorType: 'HTTP 500 Internal Server Error',
    expected: 'Handled page shows a safe server message while technical details stay in the console.',
    payload: { title: 'SERVER ERROR', accessionNumber: 'ACC-5000', author: 'Demo User' }
  },
  slow: {
    order: 8,
    icon: '…',
    title: 'Loading State and Double-Submit Protection',
    subtitle: 'Show progress and disable the submit button while a request is pending.',
    errorType: 'Slow response / pending request',
    expected: 'Handled page shows Saving..., disables the button, then restores the UI.',
    payload: { title: 'SLOW BOOK', accessionNumber: 'ACC-3000', author: 'Demo User' }
  },
  network: {
    order: 9,
    icon: '↯',
    title: 'Network Failure Handling',
    subtitle: 'Handle a request that receives no HTTP response.',
    errorType: 'Network / connection failure',
    expected: 'Handled page clearly states that the API could not be reached and that the operation is unconfirmed.',
    payload: { title: 'Network Demo Book', accessionNumber: 'ACC-9000', author: 'Demo User' },
    apiUrl: NETWORK_FAILURE_URL
  },
  success: {
    order: 10,
    icon: '201',
    title: 'Success Feedback and Stable UI',
    subtitle: 'Confirm completion and return the form to a stable state.',
    errorType: 'HTTP 201 Created',
    expected: 'Handled page confirms the created stub record and restores the Save button.',
    payload: { title: 'Refactoring', accessionNumber: 'ACC-2101', author: 'Martin Fowler' }
  }
}

const app = document.querySelector('#app')
const params = new URLSearchParams(window.location.search)
const techniqueKey = params.get('technique')
const mode = params.get('mode')

function hrefFor(values = {}) {
  const url = new URL(window.location.href)
  url.search = ''
  Object.entries(values).forEach(([key, value]) => {
    if (value) url.searchParams.set(key, value)
  })
  return `${url.pathname}${url.search}`
}

function pageHeader() {
  return `
    <header class="topbar">
      <div class="topbar-inner">
        <div>
          <h1>KHAE Library Management</h1>
          <p>Error Handling and User Feedback Demonstration</p>
        </div>
        <span class="stub-badge">STUB BACKEND ONLY</span>
      </div>
    </header>
  `
}

function homeLink(label = '← Error Handling Techniques') {
  return `<a class="text-link" href="${hrefFor()}">${label}</a>`
}

function renderIndex() {
  const cards = Object.entries(techniques)
    .sort(([, a], [, b]) => a.order - b.order)
    .map(([key, technique]) => `
      <a class="technique-card" href="${hrefFor({ technique: key })}">
        <div class="technique-icon">${technique.icon}</div>
        <div>
          <span class="technique-number">Technique ${technique.order}</span>
          <h2>${technique.title}</h2>
          <p>${technique.subtitle}</p>
          <span class="error-chip">${technique.errorType}</span>
        </div>
        <span class="card-arrow">→</span>
      </a>
    `).join('')

  app.innerHTML = `
    ${pageHeader()}
    <main class="page-shell">
      <section class="hero-card">
        <div class="demo-note"><strong>Architecture:</strong> Vite frontend + Spring Boot deterministic stub API. No database, JPA, JDBC, repository, datasource, or Flyway.</div>
        <p class="eyebrow">Presenter Home Page</p>
        <h2>Error Handling Techniques</h2>
        <p class="lead">Select one technique. The next page gives two large links: <strong>Without Error Handling</strong> and <strong>With Error Handling</strong>. The presenter does not need to type or copy any URL.</p>
        <div class="flow-strip">
          <span>1. Choose technique</span><b>→</b><span>2. Choose comparison</span><b>→</b><span>3. Run the same scenario</span>
        </div>
      </section>
      <section class="technique-grid" aria-label="Error handling techniques">
        ${cards}
      </section>
    </main>
  `
}

function renderTechniqueChoice(key) {
  const technique = techniques[key]
  if (!technique) {
    renderIndex()
    return
  }

  app.innerHTML = `
    ${pageHeader()}
    <main class="page-shell">
      <nav class="breadcrumb">${homeLink()}</nav>
      <section class="choice-card">
        <div class="choice-title">
          <div class="technique-icon large">${technique.icon}</div>
          <div>
            <p class="eyebrow">Technique ${technique.order}</p>
            <h2>${technique.title}</h2>
            <p class="lead">${technique.subtitle}</p>
          </div>
        </div>

        <div class="scenario-facts">
          <div><span>Error / condition</span><strong>${technique.errorType}</strong></div>
          <div><span>API contract</span><strong>${key === 'network' ? 'Same intentionally unreachable teaching endpoint' : 'POST /rest/demo/books'}</strong></div>
          <div><span>Expected handled result</span><strong>${technique.expected}</strong></div>
        </div>

        <h3>Choose what the students should see</h3>
        <div class="comparison-links">
          <a class="compare-link poor" href="${hrefFor({ technique: key, mode: 'without' })}">
            <span class="compare-label">Screen A</span>
            <strong>Without Error Handling</strong>
            <small>Show the problem first</small>
            <span class="compare-arrow">Open →</span>
          </a>
          <a class="compare-link good" href="${hrefFor({ technique: key, mode: 'handled' })}">
            <span class="compare-label">Screen B</span>
            <strong>With Error Handling</strong>
            <small>Show the corrected behaviour</small>
            <span class="compare-arrow">Open →</span>
          </a>
        </div>

        <div class="teaching-callout"><strong>Teaching rule:</strong> Use both links for the same technique. The stub input is preloaded automatically so the only visible difference is the frontend handling.</div>
      </section>
    </main>
  `
}

function formTemplate(currentMode, technique) {
  const isHandled = currentMode === 'handled'
  return `
    <section class="demo-screen">
      <div class="screen-heading ${isHandled ? 'with' : 'without'}">
        <div>
          <span class="eyebrow">${isHandled ? 'Recommended Practice' : 'Poor Practice'}</span>
          <h2>${technique.title}: ${isHandled ? 'With Error Handling' : 'Without Error Handling'}</h2>
        </div>
        <span class="mode-badge">${isHandled ? 'HANDLED' : 'NO HANDLING'}</span>
      </div>

      <div class="same-scenario-banner">
        <strong>Same teaching scenario:</strong> ${technique.errorType}
        <span>•</span>
        <strong>${techniqueKey === 'network' ? 'Same unreachable endpoint' : 'Same POST /rest/demo/books endpoint'}</strong>
      </div>

      <div class="teaching-grid">
        <form id="bookForm" class="book-form" novalidate>
          <label>
            Book Title
            <input id="title" name="title" value="${escapeHtml(technique.payload.title)}" />
            <small id="titleError" class="field-error"></small>
          </label>
          <label>
            Accession Number
            <input id="accessionNumber" name="accessionNumber" value="${escapeHtml(technique.payload.accessionNumber)}" />
            <small id="accessionNumberError" class="field-error"></small>
          </label>
          <label>
            Author
            <input id="author" name="author" value="${escapeHtml(technique.payload.author)}" />
          </label>
          <button id="saveButton" type="submit">Save Book</button>
          <div id="statusArea" class="status-area" role="status" aria-live="polite"></div>
        </form>

        <aside class="observation-panel">
          <h3>What Students Should Observe</h3>
          ${isHandled ? handledObservationList(techniqueKey) : unhandledObservationList(techniqueKey)}
          <div class="developer-box">
            <strong>Developer Console</strong>
            <p>Keep DevTools open. Compare the technical result with the message shown to the librarian.</p>
          </div>
        </aside>
      </div>
    </section>
  `
}

function escapeHtml(value) {
  return String(value)
    .replaceAll('&', '&amp;')
    .replaceAll('"', '&quot;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
}

function unhandledObservationList(key) {
  const specific = {
    'client-validation': 'The invalid form is sent to the backend instead of being stopped in the browser.',
    'backend-validation': 'The HTTP 400 details are not mapped to the field that needs correction.',
    duplicate: 'The duplicate conflict becomes only a generic Error message.',
    'session-expired': 'The user is not told to sign in again.',
    forbidden: 'The user cannot tell that the problem is permission rather than authentication.',
    'not-found': 'The user gets no useful not-found guidance.',
    'server-error': 'The user sees the same generic message as every other failure.',
    slow: 'The Save button remains enabled while the slow request is pending.',
    network: 'The user gets only Error and cannot tell whether the operation was confirmed.',
    success: 'The page says Done without useful confirmation of what was created.'
  }[key]
  return `<ul><li>${specific}</li><li>No field-level interpretation.</li><li>No carefully designed user feedback.</li><li>The console contains more useful information than the visible UI.</li></ul>`
}

function handledObservationList(key) {
  const specific = {
    'client-validation': 'Validation runs before fetch(), highlights the title field, and sends no request.',
    'backend-validation': 'The backend field name is mapped to the Accession Number input.',
    duplicate: 'The 409 response highlights the duplicate accession number and preserves other values.',
    'session-expired': 'The 401 response becomes a clear session-expired instruction.',
    forbidden: 'The 403 response becomes a permission message.',
    'not-found': 'The 404 response becomes a meaningful Library not-found message.',
    'server-error': 'The 500 response becomes a safe message without exposing the internal exception.',
    slow: 'The button is disabled, Saving... is shown, and the UI is restored in finally.',
    network: 'The user is told that the API cannot be reached and the operation is not confirmed.',
    success: 'The user receives a success message containing the demo record ID.'
  }[key]
  return `<ul><li>${specific}</li><li>Technical details remain available in the console.</li><li>The screen returns to a stable state after the operation.</li><li>Valid form data is preserved when correction is possible.</li></ul>`
}

function renderDemo(key, currentMode) {
  const technique = techniques[key]
  if (!technique || !['without', 'handled'].includes(currentMode)) {
    renderTechniqueChoice(key)
    return
  }

  const otherMode = currentMode === 'handled' ? 'without' : 'handled'
  const otherLabel = otherMode === 'handled' ? 'With Error Handling' : 'Without Error Handling'

  app.innerHTML = `
    ${pageHeader()}
    <main class="page-shell">
      <nav class="breadcrumb">
        ${homeLink()}
        <span>/</span>
        <a class="text-link" href="${hrefFor({ technique: key })}">${technique.title}</a>
      </nav>

      <div class="demo-toolbar">
        <a class="secondary-link" href="${hrefFor({ technique: key })}">← Comparison Page</a>
        <a class="switch-link" href="${hrefFor({ technique: key, mode: otherMode })}">Switch to ${otherLabel} →</a>
      </div>

      ${formTemplate(currentMode, technique)}
    </main>
  `

  attachDemoHandler(technique, currentMode)
}

function payloadFromForm() {
  return {
    title: document.querySelector('#title').value,
    accessionNumber: document.querySelector('#accessionNumber').value,
    author: document.querySelector('#author').value
  }
}

function clearErrors() {
  for (const id of ['title', 'accessionNumber']) {
    document.querySelector(`#${id}`).classList.remove('invalid')
    document.querySelector(`#${id}Error`).textContent = ''
  }
}

function showFieldError(field, message) {
  const input = document.querySelector(`#${field}`)
  const error = document.querySelector(`#${field}Error`)
  if (!input || !error) return false
  input.classList.add('invalid')
  error.textContent = message
  return true
}

function validateClient(payload) {
  let valid = true
  if (!payload.title.trim()) {
    showFieldError('title', 'Book title is required.')
    valid = false
  }
  if (!payload.accessionNumber.trim()) {
    showFieldError('accessionNumber', 'Accession number is required.')
    valid = false
  }
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

function attachDemoHandler(technique, currentMode) {
  const form = document.querySelector('#bookForm')
  const statusArea = document.querySelector('#statusArea')
  const saveButton = document.querySelector('#saveButton')
  const apiUrl = technique.apiUrl ?? DEFAULT_API_URL

  form.addEventListener('submit', async (event) => {
    event.preventDefault()
    clearErrors()
    statusArea.className = 'status-area'
    statusArea.textContent = ''
    const payload = payloadFromForm()

    if (currentMode === 'handled' && !validateClient(payload)) {
      statusArea.className = 'status-area error-message'
      statusArea.textContent = 'Please correct the highlighted fields before saving.'
      return
    }

    if (currentMode === 'handled') {
      saveButton.disabled = true
      saveButton.textContent = 'Saving...'
      statusArea.className = 'status-area loading-message'
      statusArea.innerHTML = '<span class="spinner" aria-hidden="true"></span> Saving the Book record. Please wait...'
    }

    try {
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      })

      if (currentMode === 'without') {
        if (!response.ok) {
          console.error('Raw stub API failure:', response.status, await response.text())
          statusArea.className = 'status-area generic-error'
          statusArea.textContent = 'Error'
          return
        }
        const body = await response.json()
        console.log('Stub response:', body)
        statusArea.className = 'status-area generic-success'
        statusArea.textContent = 'Done'
        return
      }

      const body = await response.json().catch(() => ({}))
      if (!response.ok) {
        console.error('Handled stub request failed:', { status: response.status, body })
        if (body.field && showFieldError(body.field, friendlyMessage(body, response.status))) {
          statusArea.className = 'status-area error-message'
          statusArea.textContent = 'The Book was not saved. Correct the highlighted field and try again.'
        } else {
          statusArea.className = 'status-area error-message'
          statusArea.textContent = friendlyMessage(body, response.status)
        }
        return
      }

      console.log('Handled stub request succeeded:', body)
      statusArea.className = 'status-area success-message'
      statusArea.textContent = `Book saved successfully by the stub API. Demo record ID: ${body.id}.`
    } catch (error) {
      console.error('Network failure:', error)
      if (currentMode === 'without') {
        statusArea.className = 'status-area generic-error'
        statusArea.textContent = 'Error'
      } else {
        statusArea.className = 'status-area error-message'
        statusArea.textContent = 'Unable to connect to the Library API. The request was not confirmed. Check the network or backend and try again.'
      }
    } finally {
      if (currentMode === 'handled') {
        saveButton.disabled = false
        saveButton.textContent = 'Save Book'
      }
    }
  })
}

if (!techniqueKey) {
  renderIndex()
} else if (!mode) {
  renderTechniqueChoice(techniqueKey)
} else {
  renderDemo(techniqueKey, mode)
}
