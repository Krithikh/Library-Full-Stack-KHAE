import './style.css'

const API_BASE_URL = (import.meta.env.VITE_API_BASE_URL || '').replace(/\/+$/, '')
const USE_STUBS = import.meta.env.VITE_USE_STUBS !== 'false'
const DEFAULT_API_URL = `${API_BASE_URL}/rest/demo/books`
const NETWORK_FAILURE_URL = 'http://localhost:65530/rest/demo/books'

const categories = {
  'client-validation': {
    order: 1,
    icon: '✓',
    title: 'Client-Side Validation',
    errorType: 'Missing required field',
    payload: { title: '', accessionNumber: 'ACC-2102', author: 'Demo Author' },
    stubResponse: {
      status: 400,
      body: { field: 'title', message: 'Book title is required.' }
    }
  },
  'backend-validation': {
    order: 2,
    icon: '400',
    title: 'Backend Validation',
    errorType: 'HTTP 400 Bad Request',
    payload: { title: 'Domain-Driven Design', accessionNumber: 'BAD-2103', author: 'Eric Evans' },
    stubResponse: {
      status: 400,
      body: { field: 'accessionNumber', message: 'Accession number must start with ACC-.' }
    }
  },
  duplicate: {
    order: 3,
    icon: '409',
    title: 'Business Conflict',
    errorType: 'HTTP 409 Conflict',
    payload: { title: 'Clean Code', accessionNumber: 'ACC-0001', author: 'Robert C. Martin' },
    stubResponse: {
      status: 409,
      body: { message: 'A book with accession number ACC-0001 already exists.' }
    }
  },
  'session-expired': {
    order: 4,
    icon: '401',
    title: 'Authentication Error',
    errorType: 'HTTP 401 Unauthorized',
    payload: { title: 'SESSION EXPIRED', accessionNumber: 'ACC-4010', author: 'Demo User' },
    stubResponse: {
      status: 401,
      body: { message: 'Your session has expired. Please sign in again.' }
    }
  },
  forbidden: {
    order: 5,
    icon: '403',
    title: 'Authorization Error',
    errorType: 'HTTP 403 Forbidden',
    payload: { title: 'FORBIDDEN BOOK', accessionNumber: 'ACC-4030', author: 'Demo User' },
    stubResponse: {
      status: 403,
      body: { message: 'You do not have permission to create this book.' }
    }
  },
  'not-found': {
    order: 6,
    icon: '404',
    title: 'Not Found',
    errorType: 'HTTP 404 Not Found',
    payload: { title: 'MISSING BOOK', accessionNumber: 'ACC-4040', author: 'Demo User' },
    stubResponse: {
      status: 404,
      body: { message: 'The requested library resource was not found.' }
    }
  },
  'server-error': {
    order: 7,
    icon: '500',
    title: 'Server Error',
    errorType: 'HTTP 500 Internal Server Error',
    payload: { title: 'SERVER ERROR', accessionNumber: 'ACC-5000', author: 'Demo User' },
    stubResponse: {
      status: 500,
      body: { message: 'The server could not complete the request. Please try again later.' }
    }
  },
  slow: {
    order: 8,
    icon: '…',
    title: 'Loading State',
    errorType: 'Slow response',
    payload: { title: 'SLOW BOOK', accessionNumber: 'ACC-3000', author: 'Demo User' },
    stubResponse: {
      status: 201,
      delayMs: 2500,
      body: { id: 3000, message: 'Book saved after a simulated slow response.' }
    }
  },
  network: {
    order: 9,
    icon: '↯',
    title: 'Network Failure',
    errorType: 'Connection failure',
    payload: { title: 'Network Demo Book', accessionNumber: 'ACC-9000', author: 'Demo User' },
    apiUrl: NETWORK_FAILURE_URL,
    stubResponse: {
      delayMs: 700,
      networkError: true
    }
  },
  success: {
    order: 10,
    icon: '201',
    title: 'Success Feedback',
    errorType: 'HTTP 201 Created',
    payload: { title: 'Refactoring', accessionNumber: 'ACC-2101', author: 'Martin Fowler' },
    stubResponse: {
      status: 201,
      body: { id: 2101, message: 'Book created successfully.' }
    }
  }
}

const app = document.querySelector('#app')
const params = new URLSearchParams(window.location.search)
const categoryKey = params.get('technique')
const mode = params.get('mode')

function hrefFor(values = {}) {
  const url = new URL(window.location.href)
  url.search = ''

  Object.entries(values).forEach(([key, value]) => {
    if (value) {
      url.searchParams.set(key, value)
    }
  })

  return `${url.pathname}${url.search}`
}

function pageHeader() {
  const dataSource = USE_STUBS
    ? 'Frontend sample responses'
    : 'Spring Boot backend'

  return `
    <header class="topbar">
      <div class="topbar-inner">
        <div>
          <h1>KHAE Library Management</h1>
          <p>
            Error Handling and User Feedback
            · Data source: ${dataSource}
          </p>
        </div>
      </div>
    </header>
  `
}

function renderIndex() {
  const cards = Object.entries(categories)
    .sort(([, a], [, b]) => a.order - b.order)
    .map(([key, category]) => `
      <article class="category-card">
        <div class="category-title-row">

          <div class="category-icon">
            ${category.icon}
          </div>

          <div>
            <span class="category-number">
              Category ${category.order}
            </span>

            <h2>
              ${category.title}
            </h2>

            <span class="error-chip">
              ${category.errorType}
            </span>
          </div>

        </div>

        <div class="category-actions">

          <a
            class="page-link without-link"
            href="${hrefFor({
              technique: key,
              mode: 'without'
            })}"
          >
            Without Error Handling
          </a>

          <a
            class="page-link handled-link"
            href="${hrefFor({
              technique: key,
              mode: 'handled'
            })}"
          >
            With Error Handling
          </a>

        </div>
      </article>
    `)
    .join('')

  app.innerHTML = `
    ${pageHeader()}

    <main class="page-shell">

      <section class="page-title-card">
        <h2>Error Handling Categories</h2>
      </section>

      <section
        class="category-grid"
        aria-label="Error handling categories"
      >
        ${cards}
      </section>

    </main>
  `
}

function escapeHtml(value) {
  return String(value)
    .replaceAll('&', '&amp;')
    .replaceAll('"', '&quot;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
}

function renderApplicationPage(key, currentMode) {
  const category = categories[key]

  if (
    !category ||
    !['without', 'handled'].includes(currentMode)
  ) {
    renderIndex()
    return
  }

  const handled = currentMode === 'handled'

  const otherMode =
    handled ? 'without' : 'handled'

  const otherLabel =
    handled
      ? 'Without Error Handling'
      : 'With Error Handling'

  app.innerHTML = `
    ${pageHeader()}

    <main class="page-shell">

      <nav class="page-nav">

        <a href="${hrefFor()}">
          ← Error Handling Categories
        </a>

        <a
          class="switch-link"
          href="${hrefFor({
            technique: key,
            mode: otherMode
          })}"
        >
          ${otherLabel}
        </a>

      </nav>

      <section class="application-card">

        <div class="application-heading">

          <div>
            <span class="category-number">
              ${category.errorType}
            </span>

            <h2>
              ${category.title}
            </h2>
          </div>

          <span
            class="mode-badge ${
              handled
                ? 'handled'
                : 'without'
            }"
          >
            ${
              handled
                ? 'With Error Handling'
                : 'Without Error Handling'
            }
          </span>

        </div>

        <form
          id="bookForm"
          class="book-form"
          novalidate
        >

          <label>

            Book Title

            <input
              id="title"
              name="title"
              value="${escapeHtml(
                category.payload.title
              )}"
            />

            <small
              id="titleError"
              class="field-error"
            ></small>

          </label>

          <label>

            Accession Number

            <input
              id="accessionNumber"
              name="accessionNumber"
              value="${escapeHtml(
                category.payload.accessionNumber
              )}"
            />

            <small
              id="accessionNumberError"
              class="field-error"
            ></small>

          </label>

          <label>

            Author

            <input
              id="author"
              name="author"
              value="${escapeHtml(
                category.payload.author
              )}"
            />

          </label>

          <button
            id="saveButton"
            type="submit"
          >
            Save Book
          </button>

          <div
            id="statusArea"
            class="status-area"
            role="status"
            aria-live="polite"
          ></div>

        </form>

      </section>

    </main>
  `

  attachApplicationHandler(
    category,
    currentMode
  )

  attachInputResetHandlers()
}

function payloadFromForm() {
  return {
    title:
      document.querySelector('#title').value,

    accessionNumber:
      document.querySelector(
        '#accessionNumber'
      ).value,

    author:
      document.querySelector('#author').value
  }
}

function clearErrors() {
  for (
    const id of [
      'title',
      'accessionNumber'
    ]
  ) {
    const input =
      document.querySelector(`#${id}`)

    const error =
      document.querySelector(
        `#${id}Error`
      )

    input?.classList.remove('invalid')

    input?.removeAttribute(
      'aria-invalid'
    )

    if (error) {
      error.textContent = ''
    }
  }
}

function showFieldError(
  field,
  message
) {
  const input =
    document.querySelector(`#${field}`)

  const error =
    document.querySelector(
      `#${field}Error`
    )

  if (!input || !error) {
    return false
  }

  input.classList.add('invalid')

  input.setAttribute(
    'aria-invalid',
    'true'
  )

  error.textContent = message

  return true
}

function validateClient(payload) {
  let valid = true

  if (!payload.title.trim()) {
    showFieldError(
      'title',
      'Book title is required.'
    )

    valid = false
  }

  if (
    !payload.accessionNumber.trim()
  ) {
    showFieldError(
      'accessionNumber',
      'Accession number is required.'
    )

    valid = false
  }

  return valid
}

function friendlyMessage(
  body,
  status
) {
  if (body?.message) {
    return body.message
  }

  if (status === 400) {
    return 'The request contains invalid data.'
  }

  if (status === 401) {
    return 'Your session has expired. Please sign in again.'
  }

  if (status === 403) {
    return 'You do not have permission to perform this operation.'
  }

  if (status === 404) {
    return 'The requested record was not found.'
  }

  if (status === 409) {
    return 'This record conflicts with an existing record.'
  }

  if (status >= 500) {
    return 'The server could not complete the request. Please try again later.'
  }

  return 'The request could not be completed.'
}

function attachInputResetHandlers() {
  const statusArea =
    document.querySelector(
      '#statusArea'
    )

  for (
    const id of [
      'title',
      'accessionNumber',
      'author'
    ]
  ) {
    const input =
      document.querySelector(`#${id}`)

    if (!input) {
      continue
    }

    input.addEventListener(
      'input',
      () => {
        input.classList.remove(
          'invalid'
        )

        input.removeAttribute(
          'aria-invalid'
        )

        const error =
          document.querySelector(
            `#${id}Error`
          )

        if (error) {
          error.textContent = ''
        }

        if (statusArea) {
          statusArea.className =
            'status-area'

          statusArea.textContent = ''
        }
      }
    )
  }
}

function wait(ms) {
  return new Promise(
    resolve =>
      setTimeout(resolve, ms)
  )
}

async function stubBookRequest(
  category,
  payload
) {
  const stub =
    category.stubResponse

  if (!stub) {
    throw new Error(
      'No sample response is configured for this demo category.'
    )
  }

  if (stub.delayMs) {
    await wait(stub.delayMs)
  }

  if (stub.networkError) {
    throw new TypeError(
      'Simulated network connection failure'
    )
  }

  const body =
    typeof stub.body === 'function'
      ? stub.body(payload)
      : stub.body

  console.info(
    'Frontend stub response:',
    {
      status: stub.status,
      body
    }
  )

  return new Response(
    JSON.stringify(body ?? {}),
    {
      status: stub.status,
      headers: {
        'Content-Type':
          'application/json'
      }
    }
  )
}

function sendBookRequest(
  category,
  payload
) {
  if (USE_STUBS) {
    console.info(
      'Backend call skipped. Using frontend sample response.'
    )

    return stubBookRequest(
      category,
      payload
    )
  }

  const apiUrl =
    category.apiUrl ??
    DEFAULT_API_URL

  return fetch(
    apiUrl,
    {
      method: 'POST',

      headers: {
        'Content-Type':
          'application/json'
      },

      body:
        JSON.stringify(payload)
    }
  )
}

function attachApplicationHandler(
  category,
  currentMode
) {
  const form =
    document.querySelector(
      '#bookForm'
    )

  const statusArea =
    document.querySelector(
      '#statusArea'
    )

  const saveButton =
    document.querySelector(
      '#saveButton'
    )

  form.addEventListener(
    'submit',
    async event => {

      event.preventDefault()

      clearErrors()

      statusArea.className =
        'status-area'

      statusArea.textContent = ''

      const payload =
        payloadFromForm()

      if (
        currentMode === 'handled' &&
        !validateClient(payload)
      ) {
        statusArea.className =
          'status-area error-message'

        statusArea.textContent =
          'Please correct the highlighted fields.'

        return
      }

      if (
        currentMode === 'handled'
      ) {
        saveButton.disabled = true

        saveButton.textContent =
          'Saving...'

        statusArea.className =
          'status-area loading-message'

        statusArea.innerHTML =
          '<span class="spinner" aria-hidden="true"></span> Saving...'
      }

      try {
        const response =
          await sendBookRequest(
            category,
            payload
          )

        if (
          currentMode === 'without'
        ) {
          if (!response.ok) {
            console.error(
              'Request failed:',
              response.status,
              await response.text()
            )

            statusArea.className =
              'status-area generic-error'

            statusArea.textContent =
              'Error'

            return
          }

          const body =
            await response.json()

          console.log(
            'Request succeeded:',
            body
          )

          statusArea.className =
            'status-area generic-success'

          statusArea.textContent =
            'Done'

          return
        }

        const body =
          await response
            .json()
            .catch(() => ({}))

        if (!response.ok) {
          console.error(
            'Request failed:',
            {
              status:
                response.status,
              body
            }
          )

          if (
            body.field &&
            showFieldError(
              body.field,
              friendlyMessage(
                body,
                response.status
              )
            )
          ) {
            statusArea.className =
              'status-area error-message'

            statusArea.textContent =
              'The Book was not saved. Correct the highlighted field.'
          } else {
            statusArea.className =
              'status-area error-message'

            statusArea.textContent =
              friendlyMessage(
                body,
                response.status
              )
          }

          return
        }

        console.log(
          'Request succeeded:',
          body
        )

        statusArea.className =
          'status-area success-message'

        statusArea.textContent =
          `Book saved successfully. Record ID: ${body.id}.`

      } catch (error) {

        console.error(
          'Network failure:',
          error
        )

        if (
          currentMode === 'without'
        ) {
          statusArea.className =
            'status-area generic-error'

          statusArea.textContent =
            'Error'
        } else {
          statusArea.className =
            'status-area error-message'

          statusArea.textContent =
            'Unable to connect to the server. Please try again.'
        }

      } finally {

        if (
          currentMode === 'handled'
        ) {
          saveButton.disabled = false

          saveButton.textContent =
            'Save Book'
        }

      }
    }
  )
}

if (
  categoryKey &&
  mode
) {
  renderApplicationPage(
    categoryKey,
    mode
  )
} else {
  renderIndex()
}
