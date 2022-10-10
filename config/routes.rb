require 'constraints/domain_constraint'

Rails.application.routes.draw do
  devise_for :users
  root 'static_pages#root'
  post '/webhooks/:source', to: 'webhooks#create'

  constraints DomainConstraint do
      scope module: :stores do
        resources :products
        root to: 'products#index', as: 'store_root'
        resource :checkout, as: 'store_checkout'
      end
  end

  resource :dashboard
  resource :checkout
  resource :store
  
  resources :accounts
  resources :payouts, only: [:create]
  resources :products
end
