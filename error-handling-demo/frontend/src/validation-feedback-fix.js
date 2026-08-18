// Keeps validation/error feedback synchronized with what the presenter types.
// This module intentionally contains only UI behaviour; it does not change the stub API.

function clearMessageWhenUserCorrects(fieldId) {
  const input = document.querySelector(`#${fieldId}`)
  const error = document.querySelector(`#${fieldId}Error`)
  const statusArea = document.querySelector('#statusArea')

  if (!input) return

  input.addEventListener('input', () => {
    // Once the learner changes a value, the old field error must no longer look current.
    input.classList.remove('invalid')
    input.removeAttribute('aria-invalid')
    if (error) error.textContent = ''

    // Remove stale result text so the screen reflects the new, not-yet-submitted input.
    if (statusArea) {
      statusArea.className = 'status-area'
      statusArea.textContent = ''
    }
  })
}

function addNetworkScenarioNote() {
  const params = new URLSearchParams(window.location.search)
  if (params.get('technique') !== 'network' || !params.get('mode')) return

  const form = document.querySelector('#bookForm')
  if (!form || form.querySelector('.network-scenario-note')) return

  const note = document.createElement('div')
  note.className = 'network-scenario-note'
  note.style.padding = '12px 14px'
  note.style.border = '1px solid #f59e0b'
  note.style.borderRadius = '8px'
  note.style.background = '#fffbeb'
  note.style.color = '#92400e'
  note.style.lineHeight = '1.5'
  note.innerHTML = '<strong>Network demo:</strong> changing Book Title will not remove this failure because this technique deliberately calls an unreachable endpoint. Choose another technique to demonstrate an input-correctable error.'
  form.prepend(note)
}

function initialiseValidationFeedbackFix() {
  // main.js renders the demonstration synchronously before this module runs.
  clearMessageWhenUserCorrects('title')
  clearMessageWhenUserCorrects('accessionNumber')
  clearMessageWhenUserCorrects('author')
  addNetworkScenarioNote()
}

initialiseValidationFeedbackFix()
