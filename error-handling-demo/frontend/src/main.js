import './style.css'

const apiUrl = '/rest/demo/books'

const app = document.querySelector('#app')

app.innerHTML = `
  <header class="topbar">
    <div>
      <h1>KHAE Library Management</h1>
      <p>Error Handling and User Feedback Demonstration</p>
    </div>
  </header>

  <main class="page-shell">
    <section class="intro-card">
      <h2>Compare the Same Create Book Operation</h2>
      <p>Both screens call the same Spring Boot endpoint. The difference is the frontend behaviour. Use the same inputs on both screens and compare what the librarian sees.</p>
      <div class="scenario-strip">
        <span><strong>Duplicate:</strong> ACC-0001</span>
        <span><strong>Slow response:</strong> title SLOW BOOK</span>
        <span><strong>Server failure:</strong> title SERVER ERROR</span>
      </div>
    </section>

    <nav class="screen-tabs" aria-label="Demo screens">
      <button id="showWithout" class="tab active">Screen 1: Without Error Handling</button>
      <button id="showWith" class="tab">Screen 2: With Error Handling</button>
    </nav>

    <section id="withoutScreen" class="demo-screen"></section>
    <section id="withScreen" class="demo-screen hidden"></section>
  </main>
`

const formTemplate = (mode) => `
  <div class="screen-heading ${mode}">
    <div>
      <span class="eyebrow">${mode === 'without' ? 'Poor Practice Demonstration' : 'Recommended Practice Demonstration'}</span>
      <h2>${mode === 'without' ? 'Create Book Without Error Handling' : 'Create Book With Error Handling'}</h2>
    </div>
    <span class="mode-badge">${mode === 'without' ? 'NO HANDLING' : 'HANDLED'}</span>
  </div>

  <div class="teaching-grid">
    <form id="${mode}Form" class="book-form" novalidate>
      <label>
        Book Title
        <input id="${mode}Title" name="title" placeholder="Example: Clean Architecture" />
        <small id="${mode}TitleError" class="field-error"></small>
      </label>

      <label>
        Accession Number
        <input id="${mode}Accession" name="accessionNumber" placeholder="Example: ACC-1001" />
        <small id="${mode}AccessionError" class="field-error"></small>
      </label>

      <label>
        Author
        <input id="${mode}Author" name="author" placeholder="Example: Robert C. Martin" />
      </label>

      <button id="${mode}Save" type="submit">Save Book</button>
      <div id="${mode}Status" class="status-area" role="status" aria-live="polite"></div>
    </form>

    <aside class="observation-panel">
      <h3>What Students Should Observe</h3>
      <ul id="${mode}Observations"></ul>
      <div class="developer-box">
        <strong>Developer Console</strong>
        <p>Open browser developer tools and compare the technical error with what the user sees.</p>
      </div>
    </aside>
  </div>
`

document.querySelector('#withoutScreen').innerHTML = formTemplate('without')
document.querySelector('#withScreen').innerHTML = formTemplate('with')

document.querySelector('#withoutObservations').innerHTML = `
  <li>No frontend validation before the API call.</li>
  <li>No loading state and the Save button stays enabled.</li>
  <li>No field highlighting or useful explanation.</li>
  <li>The user receives only a generic failure message.</li>
  <li>Technical details remain mainly in the console.</li>
`

document.querySelector('#withObservations').innerHTML = `
  <li>Required fields are validated before the API call.</li>
  <li>The Save button is disabled while the request is running.</li>
  <li>Backend field errors are displayed beside the correct field.</li>
  <li>Network, conflict, and server failures become meaningful messages.</li>
  <li>Success feedback confirms what happened and the UI is restored in finally.</li>
`

function switchScreen(showWith) {
  document.querySelector('#withoutScreen').classList.toggle('hidden', showWith)
  document.querySelector('#withScreen').classList.toggle('hidden', !showWith)
  document.querySelector('#showWithout').classList.toggle('active', !showWith)
  document.querySelector('#showWith').classList.toggle('active', showWith)
}

document.querySelector('#showWithout').addEventListener('click', () => switchScreen(false))
document.querySelector('#showWith').addEventListener('click', () => switchScreen(true))

function values(mode) {
  return {
    title: document.querySelector(`#${mode}Title`).value,
    accessionNumber: document.querySelector(`#${mode}Accession`).value,
    author: document.querySelector(`#${mode}Author`).value
  }
}

// Screen 1 intentionally demonstrates poor practice.
// It sends data without validation, does not show loading feedback, and does not interpret backend errors.
document.querySelector('#withoutForm').addEventListener('submit', async (event) => {
  event.preventDefault()
  const status = document.querySelector('#withoutStatus')
  status.className = 'status-area'
  status.textContent = ''

  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(values('without'))
    })

    if (!response.ok) {
      console.error('Raw backend failure:', response.status, await response.text())
      status.className = 'status-area generic-error'
      status.textContent = 'Error'
      return
    }

    const body = await response.json()
    console.log('Created book:', body)
    status.textContent = 'Done'
  } catch (error) {
    console.error('Network failure:', error)
    // Deliberately weak feedback: the user receives no explanation of the network problem.
    status.className = 'status-area generic-error'
    status.textContent = 'Error'
  }
})

function clearHandledErrors() {
  for (const id of ['withTitle', 'withAccession']) {
    document.querySelector(`#${id}`).classList.remove('invalid')
  }
  document.querySelector('#withTitleError').textContent = ''
  document.querySelector('#withAccessionError').textContent = ''
}

function showFieldError(field, message) {
  const mapping = {
    title: ['withTitle', 'withTitleError'],
    accessionNumber: ['withAccession', 'withAccessionError']
  }
  const target = mapping[field]
  if (!target) return false
  document.querySelector(`#${target[0]}`).classList.add('invalid')
  document.querySelector(`#${target[1]}`).textContent = message
  return true
}

function validateHandledForm(payload) {
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

function friendlyMessage(errorBody, status) {
  if (errorBody?.message) return errorBody.message
  if (status === 401) return 'Your Library session has expired. Please sign in again.'
  if (status === 403) return 'You do not have permission to perform this Library operation.'
  if (status === 404) return 'The requested Library record was not found.'
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
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })

    const body = await response.json().catch(() => ({}))

    if (!response.ok) {
      console.error('Create Book failed:', { status: response.status, body })
      if (body.field && showFieldError(body.field, friendlyMessage(body, response.status))) {
        statusArea.className = 'status-area error-message'
        statusArea.textContent = 'The Book was not saved. Correct the highlighted field and try again.'
      } else {
        statusArea.className = 'status-area error-message'
        statusArea.textContent = friendlyMessage(body, response.status)
      }
      return
    }

    console.log('Create Book succeeded:', body)
    statusArea.className = 'status-area success-message'
    statusArea.textContent = `Book saved successfully. Library record ID: ${body.id}.`
  } catch (error) {
    console.error('Create Book network failure:', error)
    statusArea.className = 'status-area error-message'
    statusArea.textContent = 'Unable to connect to the Library server. Check the backend and try again.'
  } finally {
    saveButton.disabled = false
    saveButton.textContent = 'Save Book'
  }
})
