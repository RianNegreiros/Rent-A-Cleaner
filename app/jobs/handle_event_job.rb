class HandleEventJob < ApplicationJob
  queue_as :default

  def perform(event)
    case event.source
    when 'stripe'
      handle_stripe_event(event)
    end
  end

  def handle_stripe_event(event)
    stripe_event = Stripe::Event.construct_from(event.data)
    case stripe_event_type
    when 'customer.created'
      handle_customer_created(stripe_event)
    end
  end

  def handle_customer_created(stripe_event)
    puts "stripe customer created"
  end
end
