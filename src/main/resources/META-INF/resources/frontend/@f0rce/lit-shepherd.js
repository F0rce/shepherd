/**
@license MIT
Copyright 2022 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html } from 'lit-element'

import Shepherd from 'shepherd.js'

class LitShepherd extends LitElement {
  static get properties () {
    return {
      tours: { type: String }
    }
  }

  render () {
    return html`<slot></slot>`
  }

  connectedCallback () {
    super.connectedCallback()

    this.tourMap = new Map()
  }

  updated (changedProperties) {
    for (let i = 0; i < changedProperties.size; i++) {
      const toUpdate = Array.from(changedProperties.keys())[i]
      const funcToCall = toUpdate + 'Changed'
      if (typeof this[funcToCall] === 'function') {
        this[funcToCall]() // This line is freaking cool
      }
    }
  }

  createTour (config) {
    const configParsed = JSON.parse(config)

    configParsed.tourOptions.stepsContainer = document.getElementById(
      configParsed.id
    )
    configParsed.tourOptions.modalContainer = document.getElementById(
      configParsed.id
    )

    if (configParsed.tourOptions.defaultStepOptions !== undefined) {
      for (const button of configParsed.tourOptions.defaultStepOptions
        .buttons) {
        const action = button.action
        if (action !== undefined) {
          switch (action) {
            case 'back':
              button.action = function () {
                return this.back()
              }
              break
            case 'cancel':
              button.action = function () {
                return this.cancel()
              }
              break
            case 'complete':
              button.action = function () {
                return this.complete()
              }
              break
            case 'next':
              button.action = function () {
                return this.next()
              }
              break
          }
        }
      }

      if (
        configParsed.tourOptions.defaultStepOptions.progressIndicator !==
        undefined
      ) {
        switch (configParsed.tourOptions.defaultStepOptions.progressIndicator) {
          case 'header':
            configParsed.tourOptions.defaultStepOptions.when = {
              show: function () {
                const currentStepElement = this.getCurrentStep().el
                const header =
                  currentStepElement.querySelector('.shepherd-header')
                const progress = document.createElement('span')
                progress.style['margin-right'] = '15px'
                progress.innerText = `${
                  this.steps.indexOf(this.getCurrentStep()) + 1
                }/${this.steps.length}`
                header.insertBefore(
                  progress,
                  currentStepElement.querySelector('.shepherd-cancel-icon')
                )
              }
            }
            delete configParsed.tourOptions.defaultStepOptions
              .progressIndicator
            break
        }
      }

      delete configParsed.tourOptions.defaultStepOptions.arrow
      delete configParsed.tourOptions.defaultStepOptions.id
    }

    configParsed.tourOptions.defaultStepOptions.popperOptions = {
      modifiers: [{ name: 'offset', options: { offset: [0, 12] } }]
    }

    const tour = new Shepherd.Tour(configParsed.tourOptions)
    const tourEventDiv = document.getElementById(configParsed.id)

    tour.on('complete', (evt) => {
      tourEventDiv.dispatchEvent(new window.CustomEvent('tour-complete'))
    })

    tour.on('cancel', (evt) => {
      tourEventDiv.dispatchEvent(new window.CustomEvent('tour-cancel'))
    })

    tour.on('start', (evt) => {
      tourEventDiv.dispatchEvent(new window.CustomEvent('tour-start'))
    })

    tour.on('show', (current, previous, tour) => {
      tourEventDiv.dispatchEvent(
        new window.CustomEvent('tour-show', {
          detail: {
            current: current.id
          }
        })
      )
    })

    this.tourMap.set(configParsed.id, tour)
  }

  removeTour (tour) {
    const parsed = JSON.parse(tour)

    if (this.tourMap.has(parsed.id)) {
      this.tourMap.delete(parsed.id)
    }
  }

  addStep (tourId, config, pos) {
    const configParsed = JSON.parse(config)

    const tour = this.tourMap.get(tourId)

    if (tour === undefined) {
      console.error('[lit-shepherd] Tour not found')
      return
    }

    for (const button of configParsed.stepOptions.buttons) {
      const action = button.action
      if (action !== undefined) {
        switch (action) {
          case 'back':
            button.action = tour.back
            break
          case 'cancel':
            button.action = tour.cancel
            break
          case 'complete':
            button.action = tour.complete
            break
          case 'next':
            button.action = tour.next
            break
        }
      }
    }

    if (configParsed.stepOptions.progressIndicator !== undefined) {
      switch (configParsed.stepOptions.progressIndicator) {
        case 'header':
          configParsed.stepOptions.when = {
            show: function () {
              const currentStepElement = tour.getCurrentStep().el
              const header =
                currentStepElement.querySelector('.shepherd-header')
              const progress = document.createElement('span')
              progress.style['margin-right'] = '15px'
              progress.innerText = `${
                tour.steps.indexOf(tour.getCurrentStep()) + 1
              }/${tour.steps.length}`
              header.insertBefore(
                progress,
                currentStepElement.querySelector('.shepherd-cancel-icon')
              )
            }
          }
          delete configParsed.stepOptions.progressIndicator
          break
      }
    }

    tour.addStep(configParsed.stepOptions, pos)
  }

  /**
   * Start a specific tour by tourId.
   *
   * @param {String} tourId
   */
  startTour (tourId) {
    const tour = this.tourMap.get(tourId)

    tour.start()
  }

  /**
   * Stop/Cancel a specific tour by tourId.
   *
   * @param {String} tourId
   */
  cancelTour (tourId) {
    const tour = this.tourMap.get(tourId)

    tour.cancel()
  }

  backTour (tourId) {
    const tour = this.tourMap.get(tourId)

    tour.back()
  }

  completeTour (tourId) {
    const tour = this.tourMap.get(tourId)

    tour.complete()
  }
}

window.customElements.define('lit-shepherd', LitShepherd)
